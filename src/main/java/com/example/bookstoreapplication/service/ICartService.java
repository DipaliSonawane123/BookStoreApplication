package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.CartDto;
import com.example.bookstoreapplication.model.Cart;

import java.util.List;

public interface ICartService {
    Cart addCart(CartDto cartDto);

    List<Cart> getall();

    Cart FindById(int id);

    void deleteById(int id);

     Cart editById(int id, CartDto cardDto);

    Cart changeCartQty(int cardId, int quantity);
}
