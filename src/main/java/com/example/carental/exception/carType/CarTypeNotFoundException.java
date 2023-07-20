package com.example.carental.exception.carType;

public class CarTypeNotFoundException extends RuntimeException {
    public CarTypeNotFoundException(String message) {
        super(message);
    }
}
