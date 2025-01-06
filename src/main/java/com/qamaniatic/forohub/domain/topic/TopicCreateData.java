package com.qamaniatic.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicCreateData(
        @NotBlank
        String title,
        @NotBlank
        String message) {
}
