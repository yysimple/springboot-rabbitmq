package com.jxkj.controller;

import com.jxkj.dao.BookDao;
import com.jxkj.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping("/test1")
    public void test1(){
        List<Book> list = new ArrayList<>();
        Book book1 = new Book(3,"三国演义","鲁迅");
        Book book2 = new Book(4,"红楼梦","鲁迅");
        list.add(book1);
        list.add(book2);
        bookDao.insert(list);
        List<Book> books = bookDao.findByAuthorContains("鲁迅");
        books.forEach(System.out::println);
        Book book = bookDao.findByNameEquals("呐喊");
        System.out.println(book);
    }



}
