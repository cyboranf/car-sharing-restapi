package com.example.carental.exception.insurance;

public class InvalidPolicyNumberException extends RuntimeException {
    public InvalidPolicyNumberException(String message) {
        super(message);
    }
}
