package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.BookDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService service;
    /**
     * Post Api for insert Book dta
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseDto> insertBookDetails(@RequestBody BookDto bookDto) {
        String response = service.insertBookDetails(bookDto);
        ResponseDto responseDto = new ResponseDto("Book Details Added sucessfully", response);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    /**
     * Get Api for get all Book in list
     */
    @GetMapping("/getall")
    public ResponseEntity<ResponseDto> GetAllBookDetails() {
        List<Book> response = service.getall();
        ResponseDto responseDto = new ResponseDto(" All Book with Details", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     * Get Api for retrieve Book details using id
     */
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable int Id) {
        Book response = service.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details of Book using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     * Get Api for Book details by book name
     */
    @GetMapping("/Book/{bookName}")
    public ResponseEntity<ResponseDto> getDataByBook(@PathVariable String bookName) {
        Book response = service.getByBook(bookName);
        ResponseDto respDTO = new ResponseDto("*** Data by using email ***", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    /**
     * Delete Api to delete BOok details using Book id
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int id) {
        service.getBookbyId(id);
        ResponseDto respDTO = new ResponseDto("*** Data deleted sucessfully ***", "Id:" + id + " is deleted");
        return new ResponseEntity<>(respDTO, HttpStatus.OK);

    }

    /**
     * Put Api for update data by Book id
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable int id, @Valid @RequestBody BookDto BookDto) {
        String response = service.editById(id, BookDto);
        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    /**
     * Get Api for sort data by accenting Order with price
     */
    @GetMapping("/sortByPriceAsc")
    public ResponseEntity<ResponseDto> getBookByPriceAsc() {
        List<Book> bookData = service.sortPriceLowToHigh();
        ResponseDto responseDTO = new ResponseDto("Sorted all books by price low to high ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    /**
     * Get Api for Sort data in descending order
     */
    @GetMapping("/sortByPriceDsc")
    public ResponseEntity<ResponseDto> getBookByPriceDsc() {
        List<Book> bookData = service.sortPriceHighToLow();
        ResponseDto responseDTO = new ResponseDto("Sorted all books by price high to low ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    /**
     * Post Api for Change quatity of book
     */
    @PostMapping("/change-qty")
    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam int id, @RequestParam int quantity) {
        Book response =service.changeBookQty(id, quantity);
        ResponseDto responseDTO = new ResponseDto("Book quantity changed successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


}