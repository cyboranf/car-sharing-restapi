package com.example.carental.exception.address;

public class InvalidStreetException extends RuntimeException {
    public InvalidStreetException(String message) {
        super(message);
    }
}
