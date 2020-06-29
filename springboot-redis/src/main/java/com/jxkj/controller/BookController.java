package com.jxkj.controller;

import com.jxkj.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test1")
    public void test1(){
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set("name","三国演义");
        System.out.println(ops1.get("name"));

        ValueOperations ops2 = redisTemplate.opsForValue();
        Book book = new Book();
        book.setName("红楼梦");
        book.setAuthor("曹雪芹");
        book.setId(1);
        ops2.set("book",book);

        System.out.println(ops2.get("book"));

    }
}
