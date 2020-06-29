package com.jxkj.controller;

import com.jxkj.mapper.BookMapper1;
import com.jxkj.mapper.BookMapper2;
import com.jxkj.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookController {
    /**
     * 这种方式会按照name 进行指定装配
     */
    @Resource(name = "jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;

    /**
     * 结合两个注解一起，也可以指定名称装配
     */
    @Autowired
    @Qualifier("jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;

    @Autowired
    BookMapper1 bookMapper1;

    @Autowired
    BookMapper2 bookMapper2;

    @GetMapping("/test1")
    public void test1(){
        List<Book> books1 = jdbcTemplateOne.query("select * from book",new BeanPropertyRowMapper<>(Book.class));
        List<Book> books2 = jdbcTemplateTwo.query("select * from book",new BeanPropertyRowMapper<>(Book.class));

        System.out.println("book1:" + books1);
        System.out.println("book2:" + books2);
    }

    @GetMapping("/test2")
    public void test2(){
        List<Book> list1 = bookMapper1.findAllBooks();
        List<Book> list2 = bookMapper2.findAllBooks();
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
    }

    @GetMapping("/test3")
    public void test3(){
        List<Book> list1 = bookMapper1.findAllBooks();
        System.out.println("list1:" + list1);
    }

}
