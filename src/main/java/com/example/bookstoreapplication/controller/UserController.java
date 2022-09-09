package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.LoginDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService service;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto> AddUserDetails(@Valid @RequestBody UserDto userDto) {
        String token = service.insertRecord(userDto);
        ResponseDto respDTO = new ResponseDto("*** Data Added successfully ***", token);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllUser() {
        User response = service.findAll();
        ResponseDto responseDTO = new ResponseDto("** All User List ** ", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable int Id) {
        User response = service.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details of Person using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // dipssonawane123+dips@gmail.com
    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> getDataByemail(@PathVariable String email) {
        User response = service.getByEmail(email);
        ResponseDto respDTO = new ResponseDto("*** Data by using email ***", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<ResponseDto> updateById(@Valid @RequestBody UserDto userDTO, @PathVariable String email) {
        User response = service.editByEmail(userDTO, email);
        ResponseDto respDTO = new ResponseDto(" **** Person details is updated *****", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/retrieve/{token}")
    public ResponseEntity<ResponseDto> getUserDetails(@Valid @PathVariable String token) {
        User response = service.getDataByToken(token);
        ResponseDto respDTO = new ResponseDto("Data retrieved successfully", response);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }

    //Controller
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody LoginDto loginDTO) {
        User response = service.loginUser(loginDTO);
        ResponseDto responseDTO = new ResponseDto("Login Successful!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/forgotpassword/{email}")
    public ResponseEntity<ResponseDto> forgotPasswordByemail(@PathVariable String email) {
        String response = service.forgotPassword(email);
        ResponseDto respDTO = new ResponseDto("*** Link send successfully ***", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody LoginDto loginDto) {
        String response = service.resetPassword(loginDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}