package com.qamaniatic.forohub.infra.error;

import org.springframework.validation.FieldError;

public record ErrorResponse(String error, String field) {
    public ErrorResponse(FieldError error) {
        this(error.getDefaultMessage(), error.getField());
    }

    public ErrorResponse(String error) {
        this(error, null);
    }
}
