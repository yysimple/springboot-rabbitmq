package com.jxkj.repository;

import com.jxkj.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 传入要操作的类型，和其主键的类型
 */
public interface BookRepository extends JpaRepository<Book,Integer> {
    /**
     * 获取作者吗名字开头有 name的book集合信息
     * @param name
     * @return
     */
    List<Book> getBooksByAuthorStartingWith(String name);

    /**
     * 获取价格大于 price的书籍集合
     * @param price
     * @return
     */
    List<Book> getBooksByPriceGreaterThan(Float price);

    /**
     * 通过自己定义的方式去操作比较复杂的业务
     * 通过id和作者查找到对应的书籍
     * 使用 :id :author此类的占位符，需要配合 @Param 注解一起使用 里面的参数就是:后面id
     * @param id
     * @param author
     * @return
     */
    @Query(value = "select b from Book b where b.id = :id and b.author = :author")
    Book getBookByIdAndAndAuthor(@Param("id") Integer id, @Param("author") String author);

    /**
     * 通过索引的方式进行查询
     * 通过id和name查找到对应的书籍
     * @return
     */
    @Query(value = "select b from Book b where b.id = ?1 and b.name = ?2")
    Book getBookByIdAndName(Integer id, String name);

}
