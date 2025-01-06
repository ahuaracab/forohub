package com.qamaniatic.forohub.controller;

import com.qamaniatic.forohub.domain.topic.*;
import com.qamaniatic.forohub.domain.user.User;
import com.qamaniatic.forohub.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TopicResponseData> createTopic(@RequestBody @Valid TopicCreateData topicCreateData, UriComponentsBuilder uriComponentsBuilder) {
        User user = userRepository.findById(topicCreateData.userId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Topic topic = topicRepository.save(new Topic(topicCreateData, user));
        TopicResponseData topicResponseData = new TopicResponseData(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getUser().getId());

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topicResponseData);
    }

    @GetMapping
    public ResponseEntity<Page<TopicListData>> listTopics(@PageableDefault(size = 10) Pageable pagination) {
        return ResponseEntity.ok(topicRepository.findAll(pagination).map(TopicListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid TopicUpdateData topicUpdateData) {
        Topic topic = topicRepository.getReferenceById(topicUpdateData.id());
        topic.updateTopic(topicUpdateData);
        return ResponseEntity.ok(new TopicResponseData(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getUser().getId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deactivateTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.deactivateTopic();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseData> topicResponseData(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        var topicResponseData = new TopicResponseData(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getUser().getId());
        return ResponseEntity.ok(topicResponseData);
    }

}