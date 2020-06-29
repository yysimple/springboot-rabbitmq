package com.jxkj.controller;

import com.jxkj.entity.Book;
import com.jxkj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/findAll")
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<Book> page = bookService.getBookByPage(pageRequest);
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("查询数据：" + page.getContent());
        System.out.println("当前页数：" + (page.getNumber() + 1));
        System.out.println("当前页记录数：" + page.getNumberOfElements());
        System.out.println("每页记录：" + page.getSize());
    }

    @GetMapping("/search")
    public void search() {
        List<Book> b1 = bookService.getBooksByAuthorStartingWith("鲁迅");
        List<Book> b2 = bookService.getBooksByPriceGreaterThan(28.8F);
        Book b3 = bookService.getBookByIdAndAndAuthor(1, "罗贯中");
        Book b4 = bookService.getBookByIdAndName(3, "西游记");
        System.out.println("b1:" + b1);
        System.out.println("b2:" + b2);
        System.out.println("b3:" + b3);
        System.out.println("b4:" + b4);
    }

    @GetMapping("/save")
    public void save() {
        Book book = new Book();
        book.setAuthor("鲁迅");
        book.setName("呐喊");
        book.setPrice(20F);
        bookService.save(book);
    }


}
