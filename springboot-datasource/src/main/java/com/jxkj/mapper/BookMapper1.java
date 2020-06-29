package com.jxkj.mapper;

import com.jxkj.model.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper1 {

    List<Book> findAllBooks();
}
