package com.jxkj.controller;

import com.jxkj.model.Author;
import com.jxkj.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/getbook")
    public String getBook(@ModelAttribute("b")Book book, @ModelAttribute("a")Author author){
        return book.toString() + "<--->" + author.toString();
    }
}
