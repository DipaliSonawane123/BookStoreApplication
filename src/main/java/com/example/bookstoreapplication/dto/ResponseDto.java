package com.example.bookstoreapplication.dto;

import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object object;

    public ResponseDto(String s, String response) {
        this.message = s;
        this.object = response;
    }

    public ResponseDto(String s, User details) {
        this.message = s;
        this.object = details;
    }

    public ResponseDto(String s, Book response) {
        this.message = s;
        this.object = response;

    }

    public ResponseDto(String s, Object response) {
        this.message = s;
        this.object = response;
    }


}

