package com.example.carental.exception.carStatus;

public class InvalidCarStatusException extends RuntimeException {
    public InvalidCarStatusException(String message) {
        super(message);
    }
}
