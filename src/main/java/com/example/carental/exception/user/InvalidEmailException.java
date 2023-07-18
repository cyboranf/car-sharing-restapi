package com.example.carental.exception.user;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
