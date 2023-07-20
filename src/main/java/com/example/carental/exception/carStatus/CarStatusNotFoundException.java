package com.example.carental.exception.carStatus;

public class CarStatusNotFoundException extends RuntimeException {
    public CarStatusNotFoundException(String message) {
        super(message);
    }
}
