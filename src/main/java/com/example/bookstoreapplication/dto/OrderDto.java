package com.example.bookstoreapplication.dto;

import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Valid
public class OrderDto {

    int price;
    int quantity;
    String address;
    int userid;
    int bookId;
    boolean cancel;
    LocalDate date = LocalDate.now();
}
