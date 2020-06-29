package com.jxkj.service;

import com.jxkj.entity.Book;
import com.jxkj.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    /**
     * 保存一本书的信息
     * @param book
     */
    public void save(Book book){
        bookRepository.save(book);
    }

    /**
     * 分页查找
     * @param pageable
     * @return
     */
    public Page<Book> getBookByPage(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public List<Book> getBooksByAuthorStartingWith(String name){
        return bookRepository.getBooksByAuthorStartingWith(name);
    }

    public List<Book> getBooksByPriceGreaterThan(Float price){
        return bookRepository.getBooksByPriceGreaterThan(price);
    }

    public Book getBookByIdAndAndAuthor(Integer id, String author){
        return bookRepository.getBookByIdAndAndAuthor(id,author);
    }

    public Book getBookByIdAndName(Integer id, String name){
        return bookRepository.getBookByIdAndName(id,name);
    }
}
