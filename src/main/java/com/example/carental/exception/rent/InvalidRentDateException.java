package com.example.carental.exception.rent;

public class InvalidRentDateException extends RuntimeException {
    public InvalidRentDateException(String message) {
        super(message);
    }
}
