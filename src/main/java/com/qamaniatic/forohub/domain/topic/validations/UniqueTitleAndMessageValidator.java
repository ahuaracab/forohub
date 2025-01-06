package com.qamaniatic.forohub.domain.topic.validations;

import com.qamaniatic.forohub.domain.ValidationException;
import com.qamaniatic.forohub.domain.topic.TopicCreateData;
import com.qamaniatic.forohub.domain.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueTitleAndMessageValidator implements TopicValidation {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validate(TopicCreateData topicCreateData) {
        if (topicRepository.existsByTitle(topicCreateData.title())) {
            throw new ValidationException("El título del tópico ya existe.", "title");
        }
        if (topicRepository.existsByMessage(topicCreateData.message())) {
            throw new ValidationException("El mensaje del tópico ya existe.", "message");
        }
    }
}
