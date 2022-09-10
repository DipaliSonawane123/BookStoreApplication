package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.BookDto;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.User;

import java.util.List;

public interface IBookService {

    String insertBookDetails(BookDto bookDto);

    List<Book> getall();

    Book FindById(int id);

    Book getByBook(String bookName);

    void getBookbyId(int id);

    String editById(int id, BookDto bookDTO);

    List<Book> sortPriceLowToHigh();

    List<Book> sortPriceHighToLow();


    Book changeBookQty(int id, int quantity);
}
