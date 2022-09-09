package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    String insertRecord(UserDto addressDto);

    List<User> findAll();

    Optional<User> FindById(Long id);

    List<User> getAddressBookByemail(String email);

    User editByEmail(UserDto userDTO, String email_address);

}
