package com.jxkj.controller;

import com.jxkj.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookController {

    @GetMapping("/getbook")
    public Book getbook(){
        Book book = new Book();
        book.setName("sgyy");
        book.setAuthor("lgz");
        book.setPrice(30f);
        book.setPublicationData(new Date());
        return book;
    }
}
