package com.jxkj.service;

import com.jxkj.entity.Book;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.stereotype.Component;

/**
 * 这个注解可以获取自己指定的字段：
 *
 * 也可以在 Repository 那个接口上配置：
 *
 */
@Projection(name = "book", types = {Book.class})
public interface BookService {

    Integer getId();

    String getName();

    // String getAuthor();

}
