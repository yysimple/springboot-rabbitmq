package com.jxkj.dao;

import com.jxkj.entity.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


/**
 * 还可以通过root对象生成 key
 */
@Repository
// 指定使用缓存的名字
@CacheConfig(cacheNames = "book_cache")
public class BookDao {

    /**
     * 默认的key 是 方法的参数， value是 方法的返回值
     * @param id
     * @return
     */
    @Cacheable
    public Book getBookById(Integer id){
        System.out.println("BookDao.getBookById");
        Book book = new Book();
        book.setId(id);
        book.setName("呐喊");
        book.setAuthor("鲁迅");
        return book;
    }

    @CachePut(key = "#book.id")
    public Book updatebookById(Book book){
        System.out.println("BookDao.updatebookById");
        book.setName("呐喊2");
        return book;
    }

    @CacheEvict(key = "#id")
    public void deleteById(Integer id){
        System.out.println("BookDao.deleteById");
    }
}
