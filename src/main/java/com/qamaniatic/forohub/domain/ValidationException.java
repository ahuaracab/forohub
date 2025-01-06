package com.qamaniatic.forohub.domain;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
