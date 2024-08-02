package com.elderlyCare.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationError extends RuntimeException{

    private final HttpStatus httpStatus;

    public AuthenticationError(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}