package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.exception.UserException;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repo.UserRepo;
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
            User user =new User(userDto);
            userRepo.save(user);
            String token = tokenUtil.createToken(user.getUser_id());
            String userData = "Your Details: \n"+user.getFirst_name()+"\n"+user.getLast_name()+"\n"
                    +user.getAddress()+"\n"+user.getEmail_address()+"\n" +user.getDOB()+"\n"+user.getPassword();
            emailSender.sendEmail(user.getEmail_address(),"Added Your Details", userData);
            return token;
        }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> FindById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> getAddressBookByemail(String email) {
        return userRepo.findAddressBookByemail(email);
    }

    @Override
    public User editByEmail(UserDto userDTO, String email_address) {
       List<User> userdata = userRepo.findAddressBookByemail(email_address);
        Optional<User> editdata = Optional.ofNullable(userdata.get(0));
        if (editdata.isPresent()) {
            userdata.get(0).setFirst_name(userDTO.getFirst_name());
            userdata.get(0).setLast_name(userDTO.getLast_name());
            userdata.get(0).setAddress(userDTO.getAddress());
            userdata.get(0).setEmail_address(userDTO.getEmail_address());
            userdata.get(0).setDOB(userDTO.getDOB());
            userdata.get(0).setPassword(userDTO.getPassword());
                //Email Body
                String updatedData = "Updated Details: \n" + "First Name: " + userdata.get(0).getFirst_name() + "\n" + "Last Name: " + userdata.get(0).getLast_name() + "\n"
                        + "Address: " + userdata.get(0).getAddress() + "\n" + "Email Address: " + userdata.get(0).getEmail_address() + "\n" + "DOB: " + userdata.get(0).getDOB() +"\n"
                        + "Password: " + userdata.get(0).getPassword();
                //sending email
                emailSender.sendEmail(userdata.get(0).getEmail_address(), "Data Updated!!!", updatedData);

                return userRepo.save(userdata.get(0));
            } else
                throw new UserException("Invalid Email Address: " + email_address);
        }

    }




