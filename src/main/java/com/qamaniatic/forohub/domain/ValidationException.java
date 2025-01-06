package com.qamaniatic.forohub.domain;

public class ValidationException extends RuntimeException {
    private final String field;

    public ValidationException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
