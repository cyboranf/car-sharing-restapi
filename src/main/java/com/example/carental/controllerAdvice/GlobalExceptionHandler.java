//package com.example.carental.controllerAdvice;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(UserRegistrationException.class)
//    public ResponseEntity<?> handleUserRegistrationException(UserRegistrationException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//
//    @ExceptionHandler(UserUpdateException.class)
//    public ResponseEntity<?> handleUserUpdateException(UserUpdateException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//
//}
