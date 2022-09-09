package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BookStore")
public class UserController {
    @Autowired
    IUserService service;

    @PostMapping("/insert")
    public ResponseEntity<String> AddAddressDetails(@Valid @RequestBody UserDto userDto) {
        String token = service.insertRecord(userDto);
        ResponseDto respDTO = new ResponseDto("*** Data Added successfully ***", token);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<User> allUser = service.findAll();
        ResponseDto responseDTO = new ResponseDto("** All AddressBook List ** ", allUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable Long Id) {
        Optional<User> response = service.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details of Person using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> getDataByemail(@PathVariable String email) {
        List<User> personDetailsList = service.getAddressBookByemail(email);
        ResponseDto respDTO = new ResponseDto("*** Data by using email ***", personDetailsList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    @PutMapping("/edit/{email_address}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable String email_address, @Valid @RequestBody UserDto userDTO) {
       User Details =service.editByEmail(userDTO, email_address);
        ResponseDto respDTO = new ResponseDto(" **** Person details is updated *****", Details);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

}
