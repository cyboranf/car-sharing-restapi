package com.example.carental.exception.insurance;

public class InvalidInsuranceProviderException extends RuntimeException {
    public InvalidInsuranceProviderException(String message) {
        super(message);
    }
}
