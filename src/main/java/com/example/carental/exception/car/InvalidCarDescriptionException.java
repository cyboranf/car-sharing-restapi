package com.example.carental.exception.car;

public class InvalidCarDescriptionException extends RuntimeException {
    public InvalidCarDescriptionException(String message) {
        super(message);
    }
}
