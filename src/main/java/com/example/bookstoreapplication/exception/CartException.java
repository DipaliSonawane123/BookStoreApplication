package com.example.bookstoreapplication.exception;

import com.example.bookstoreapplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public class CartException extends RuntimeException{

    public CartException(String message) {
        super(message);
    }
}