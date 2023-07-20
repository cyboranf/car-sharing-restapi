package com.example.carental.exception.car;

public class InvalidCountOfSeatsException extends RuntimeException {
    public InvalidCountOfSeatsException(String message) {
        super(message);
    }
}
