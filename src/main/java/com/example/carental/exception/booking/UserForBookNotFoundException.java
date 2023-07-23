package com.example.carental.exception.booking;

public class UserForBookNotFoundException extends RuntimeException {
    public UserForBookNotFoundException(String message) {
        super(message);
    }
}
