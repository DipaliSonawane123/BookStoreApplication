package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.BookDto;
import com.example.bookstoreapplication.dto.OrderDto;
import com.example.bookstoreapplication.model.Cart;
import com.example.bookstoreapplication.model.Order;

import java.util.List;

public interface IOrderService {
    String addOrder(OrderDto orderdto);

    List<Order> getall();

     Order FindById(int id);

    void getOrderbyId(int id);

    String editById(int id, OrderDto orderDto);
}
