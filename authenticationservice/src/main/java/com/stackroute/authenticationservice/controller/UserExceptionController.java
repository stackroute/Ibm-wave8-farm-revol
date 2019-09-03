package com.stackroute.authenticationservice.controller;


import com.stackroute.authenticationservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserExceptionController {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> notFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("track not found", HttpStatus.CONFLICT);
    }
}
