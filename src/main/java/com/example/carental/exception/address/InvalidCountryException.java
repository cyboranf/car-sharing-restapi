package com.example.carental.exception.address;

public class InvalidCountryException extends RuntimeException {
    public InvalidCountryException(String message) {
        super(message);
    }
}
