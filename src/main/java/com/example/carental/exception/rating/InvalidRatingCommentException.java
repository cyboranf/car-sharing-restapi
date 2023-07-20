package com.example.carental.exception.rating;

public class InvalidRatingCommentException extends RuntimeException {
    public InvalidRatingCommentException(String message) {
        super(message);
    }
}
