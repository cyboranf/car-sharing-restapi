package com.example.carental.exception.car;

public class InvalidCarBrandOrModelException extends RuntimeException {
    public InvalidCarBrandOrModelException(String message) {
        super(message);
    }
}
