package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.LoginDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.model.User;

public interface IUserService {
    String insertRecord(UserDto addressDto);

    

    User FindById(int id);

    User getByEmail(String email);

    User editByEmail(UserDto userDTO, String email_address);

    User getDataByToken(String token);

    User loginUser(LoginDto loginDto);

   String forgotPassword(String email);

    String resetPassword(LoginDto loginDto);

    User findAll();
}


