package com.qamaniatic.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicUpdateData(
        @NotNull Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        Boolean status) {
}
