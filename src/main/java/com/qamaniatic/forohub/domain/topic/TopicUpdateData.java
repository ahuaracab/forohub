package com.qamaniatic.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;

public record TopicUpdateData(
        @NotNull Long id,
        String title,
        String message) {
}
