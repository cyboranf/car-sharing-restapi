package com.example.carental.exception.rent;

public class RentNotFoundException extends RuntimeException{
    public RentNotFoundException(String message) {
        super(message);
    }
}
