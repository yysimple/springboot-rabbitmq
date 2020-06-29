package com.jxkj.repository;

import com.jxkj.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * CrossOrigin支持远程跨域访问，
 * 默认http://localhost:8080/books 实体类名后面加个s
 * 可以通过注解 @RepositoryRestResource
 * attribute:
 *  path: 你所要请求的路径
 *  collectionResourceRel：返回的集合的名称
 *  itemResourceRel：路径对象名称 "s": {
 *                                      "href": "http://localhost:8080/operatorBooks/2"
 *                                       }
 *  baseUrl：http://localhost:8080/operatorBooks
 */
@CrossOrigin
@RepositoryRestResource(path = "book", collectionResourceRel = "obs", itemResourceRel = "s")
public interface BookRepository extends JpaRepository<Book, Integer> {
    /**
     * 通过 @RestResource 将这个方法暴露出去
     * 查询操作  baseUrl/search/path ? rel （rel在不传参的情况下，会在后面name{?name}）
     * @param author
     * @return
     */
    @RestResource(path = "author", rel = "author")
    List<Book> getBookByAuthorContains(@Param("author") String author);

    /**
     * findByNameEquals
     * @param name
     * @return
     */
    @RestResource(path = "name", rel = "name")
    Book findByNameEquals(@Param("name") String name);

    /**
     * 不暴露此方法
     * @param book
     */
    @RestResource(exported = false)
    @Override
    void delete(Book book);
}
