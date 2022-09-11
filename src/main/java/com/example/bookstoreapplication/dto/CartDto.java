package com.example.bookstoreapplication.dto;

import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
    int userid;
    int bookId;
    int quantity;
}
