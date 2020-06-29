package com.jxkj;

import com.jxkj.dao.BookDao;
import com.jxkj.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootEhcacheApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    public void contextLoads() {
        bookDao.getBookById(1);
        bookDao.getBookById(1);
        bookDao.deleteById(1);
        Book book1 = bookDao.getBookById(1);
        System.out.println("book1 = " + book1);
        Book book = new Book();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        book.setId(1);
        bookDao.updatebookById(book);
        Book book2 = bookDao.getBookById(1);
        System.out.println("book2 = " + book2);

    }

}
