package com.example.carental.exception.user;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
