package com.example.bookstoreapplication.controller;


import com.example.bookstoreapplication.dto.BookDto;
import com.example.bookstoreapplication.dto.OrderDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.Cart;
import com.example.bookstoreapplication.model.Order;
import com.example.bookstoreapplication.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
   @Autowired
    IOrderService orderservice;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody OrderDto Orderdto) {
        String response = orderservice.addOrder(Orderdto);
        ResponseDto responseDTO = new ResponseDto("order details added", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<ResponseDto> GetAllDetails() {
        List<Order> response = orderservice.getall();
        ResponseDto responseDto = new ResponseDto(" All  order Details", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable int Id) {
        Order response = orderservice.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details of order on this id using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int id) {
        orderservice.getOrderbyId(id);
        ResponseDto respDTO = new ResponseDto("*** Data deleted sucessfully ***", "Id:" + id + " is deleted");
        return new ResponseEntity<>(respDTO, HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable int id, @Valid @RequestBody OrderDto orderDto) {
        String response = orderservice.editById(id, orderDto);
        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
