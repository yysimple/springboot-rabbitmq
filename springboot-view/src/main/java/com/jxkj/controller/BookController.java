package com.jxkj.controller;

import com.jxkj.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@SuppressWarnings("all")
public class BookController {

    @GetMapping("getbooks")
    public ModelAndView getbooks(){
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(1001);
        book1.setName("三国演义");
        book1.setAuthor("罗贯中");
        book1.setPrice(30.0f);
        Book book2 = new Book();
        book2.setId(1001);
        book2.setName("三国演义");
        book2.setAuthor("罗贯中");
        book2.setPrice(30.0f);
        books.add(book1);
        books.add(book2);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books",books);
        mv.setViewName("books");
        return mv;
    }
}
