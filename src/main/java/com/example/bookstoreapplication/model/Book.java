package com.example.bookstoreapplication.model;

import com.example.bookstoreapplication.dto.BookDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String bookName;
    String autherName;
    String  bookDescription;
   String bookImg;
   int price;
   int quantity;


   public Book(BookDto bookDto){
    this.bookName= bookDto.getBookName();
    this.autherName=bookDto.getAutherName();
    this.bookDescription= bookDto.getBookDescription();
    this.bookImg= bookDto.getBookImg();
    this.price= bookDto.getPrice();
    this.quantity= bookDto.getQuantity();
   }
}
