package com.example.carental.exception.message;

public class MessageNotFoundException extends RuntimeException{
    public MessageNotFoundException(String message) {
        super(message);
    }
}
