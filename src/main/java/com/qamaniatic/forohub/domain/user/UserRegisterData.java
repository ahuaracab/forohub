package com.qamaniatic.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterData(
        @NotBlank
        String login,
        @NotBlank
        String password) {
}