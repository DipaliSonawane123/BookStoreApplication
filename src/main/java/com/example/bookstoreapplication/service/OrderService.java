package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.BookDto;
import com.example.bookstoreapplication.dto.OrderDto;
import com.example.bookstoreapplication.exception.BookException;
import com.example.bookstoreapplication.exception.CartException;
import com.example.bookstoreapplication.exception.OrderException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.Cart;
import com.example.bookstoreapplication.model.Order;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.BookRepo;
import com.example.bookstoreapplication.repository.CartRepo;
import com.example.bookstoreapplication.repository.OrderRepo;
import com.example.bookstoreapplication.repository.UserRepo;
import com.example.bookstoreapplication.util.EmailSenderService;
import com.example.bookstoreapplication.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService
{
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    CartRepo cartRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSender;

    @Autowired
    UserRepo userRepo;
    @Autowired
    BookRepo bookRepo;

    @Override
    public String addOrder(OrderDto orderdto) {
        Optional<User> user = userRepo.findById(orderdto.getUserid());
        Optional<Book> book = bookRepo.findById(orderdto.getBookId());
        if (user.isPresent() && book.isPresent()) {
            Order orderDetails = new Order(orderdto.getDate(),orderdto.getPrice(), orderdto.getQuantity(),
                    orderdto.getAddress(),user.get(), book.get()
                    ,orderdto.isCancel());
            orderRepo.save(orderDetails);
            String token = tokenUtil.createToken(orderDetails.getOrderID());
            emailSender.sendEmail(user.get().getEmail(), "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
            return token;

        } else
            throw new OrderException(" userid and bookid is invalid");
    }

    @Override
    public List<Order> getall() {
        List<Order> order = orderRepo.findAll();
        return order;
    }

    @Override
    public Order FindById(int id) {
        Optional<Order> order = orderRepo.findById(id);
        return order.get();
    }

    @Override
    public void getOrderbyId(int id) {
        Optional<Order> findById = orderRepo.findById(id);
        if (findById.isPresent()){
            orderRepo.deleteById(id);
        } else throw new OrderException("Id:"+id+" not present");
    }

    @Override
    public String editById(int id, OrderDto orderDto) {
        Order editorder = orderRepo.findById(id).orElse(null);
        Optional<Book> book =bookRepo.findById(orderDto.getBookId());
        Optional<User> user =userRepo.findById(orderDto.getUserid());
        if (editorder != null) {
            editorder.setPrice(orderDto.getPrice());
            editorder.setQuantity(orderDto.getQuantity());
            editorder.setAddress(orderDto.getAddress());
            editorder.setUser(user.get());
            editorder.setBook(book.get());
            editorder.setCancel(orderDto.isCancel());
            orderRepo.save(editorder);
            String token = tokenUtil.createToken(editorder.getOrderID());
            emailSender.sendEmail(user.get().getEmail(), "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
            return token;
        } else
            throw new OrderException("Id:" + id + " is not present ");
    }


}
