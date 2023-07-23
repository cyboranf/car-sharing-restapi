package com.example.carental.exception.booking;

public class CarForBookNotFoundException extends RuntimeException {
    public CarForBookNotFoundException(String message) {
        super(message);
    }
}
