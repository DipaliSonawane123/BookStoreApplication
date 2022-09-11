package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.CartDto;
import com.example.bookstoreapplication.exception.CartException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.Cart;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.BookRepo;
import com.example.bookstoreapplication.repository.CartRepo;
import com.example.bookstoreapplication.repository.UserRepo;
import com.example.bookstoreapplication.util.EmailSenderService;
import com.example.bookstoreapplication.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

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
        public Cart addCart(CartDto cartDto) {
                Optional<User> user = userRepo.findById(cartDto.getUserid());
                Optional<Book> book = bookRepo.findById(cartDto.getBookId());
                if (user.isPresent() && book.isPresent()) {
                        Cart cartDetails = new Cart(user.get(), book.get(), cartDto.getQuantity());
                        cartRepo.save(cartDetails);
                        return cartDetails;

                } else
                        throw new CartException(" userid and bookid is invalid");
        }

        @Override
        public List<Cart> getall() {
                List<Cart> cart = cartRepo.findAll();
                return cart;
        }

        @Override
        public Cart FindById(int id) {
                Optional<Cart> cart = cartRepo.findById(id);
                return cart.get();
        }

        @Override
        public void deleteById(int id) {
                Optional<Cart> findById = cartRepo.findById(id);
                if (findById.isPresent()){
                        cartRepo.deleteById(id);
                } else throw new CartException("Id:"+id+" not present");

        }

        @Override
        public Cart editById(int id, CartDto cartDto) {
                Optional<Book> book =bookRepo.findById(cartDto.getBookId());
                Cart editdata = cartRepo.findById(id).orElse(null);
                if (editdata != null) {
                        editdata.setBook(book.get());
                        editdata.setQuantity(cartDto.getQuantity());
                        return cartRepo.save(editdata);
                } else
                        throw new CartException("Id:"+id+" is not present ");
        }

        @Override
        public Cart changeCartQty(int cartId, int quantity) {
                Cart cart = cartRepo.findById(cartId).orElse(null);
                if(cart == null){
                        throw new CartException("id is not found");
                }
                cart.setQuantity(quantity);
                return cartRepo.save(cart);
        }
        }
