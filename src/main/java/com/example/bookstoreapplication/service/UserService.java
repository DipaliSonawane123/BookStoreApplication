package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.LoginDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.exception.UserException;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.UserRepo;
import com.example.bookstoreapplication.util.EmailSenderService;
import com.example.bookstoreapplication.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSender;

    @Override
    public String insertRecord(UserDto userDto) throws UserException {
        User user = new User(userDto);
        userRepo.save(user);
        String token = tokenUtil.createToken(user.getUserId());
        emailSender.sendEmail(user.getEmail(), "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
        return token;
    }

    @Override
    public User findAll() {
        List<User> user = userRepo.findAll();
        return user.get(0);
    }

    @Override
    public User FindById(int id) {
        Optional<User> user = userRepo.findById(id);
        return user.get();
    }

    @Override
    public User getByEmail(String email) {
        User user = userRepo.findByEmail(email);
        return user;
    }

    @Override
    public User editByEmail(UserDto userDTO, String email) {
        User editdata = userRepo.findByEmail(email);
        if (editdata != null) {
            editdata.setFirstName(userDTO.getFirstName());
            editdata.setLastName(userDTO.getLastName());
            editdata.setEmail(userDTO.getEmail());
            editdata.setAddress(userDTO.getAddress());
            editdata.setDOB(userDTO.getDOB());
            editdata.setPassword(userDTO.getPassword());
            User user = userRepo.save(editdata);
            String token = tokenUtil.createToken(editdata.getUserId());
            emailSender.sendEmail(editdata.getEmail(), "Added Your Details/n", "http://localhost:8080/user/retrieve/" + token);
            return user;
        } else {
            throw new UserException("email:" + email + " is not present ");
        }

    }

    @Override
    public User getDataByToken(String token) {
        int Userid = tokenUtil.decodeToken(token);
        Optional<User> existingData = userRepo.findById(Userid);
        if (existingData.isPresent()) {
            return existingData.get();
        } else
            throw new UserException("Invalid Token");
    }

    @Override
    public User loginUser(LoginDto loginDto) {
        Optional<User> userDetails = Optional.ofNullable(userRepo.findByEmail(loginDto.getEmail()));
        if (userDetails.isPresent()) {
            //String pass = login.get().getPassword();
            if (userDetails.get().getPassword().equals(loginDto.getPassword())) {
                emailSender.sendEmail(userDetails.get().getEmail(), "About Login", "Login Successful!");
                return userDetails.get();
            } else
                emailSender.sendEmail(userDetails.get().getEmail(), "About Login", "Invalid password!");
            throw new UserException("Wrong Password!");
        } else
            throw new UserException("Login Failed, Wrong email or password!!!");
    }

    @Override
    public String forgotPassword(String email) {
        User editdata = userRepo.findByEmail(email);
        if (editdata != null){
            emailSender.sendEmail(editdata.getEmail(), "About Login", "http://localhost:8080/user/resetPassword");
            return "Reset link send sucessfully";
        }else
            throw new UserException("Login Failed, Wrong email or password!!!");
    }

    @Override
    public String resetPassword(LoginDto loginDTO) {
        Optional<User> userDetails = Optional.ofNullable(userRepo.findByEmail(loginDTO.getEmail()));
        String password = loginDTO.getPassword();
        if(userDetails.isPresent()){
            userDetails.get().setPassword(password);
            userRepo.save(userDetails.get());
            return "Password Changed";
        }else
            return "Invalid Email Address";
    }
    }











