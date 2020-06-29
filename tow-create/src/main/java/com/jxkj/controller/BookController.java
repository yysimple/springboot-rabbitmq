package com.jxkj.controller;

import com.jxkj.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    Book book;

    @GetMapping("/getbook")
    public String getBook(){
        return book.toString();
    }
}
