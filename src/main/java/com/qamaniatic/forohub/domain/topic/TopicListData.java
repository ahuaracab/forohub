package com.qamaniatic.forohub.domain.topic;

import java.time.LocalDateTime;

public record TopicListData(Long id, String title, String message, LocalDateTime creationDate, Boolean status, Long userId) {

    public TopicListData(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getUser().getId());
    }
}
