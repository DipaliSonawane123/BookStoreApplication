package com.example.bookstoreapplication.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

public class UserException extends RuntimeException {

    public UserException(String message) {

        super(message);
    }
}

