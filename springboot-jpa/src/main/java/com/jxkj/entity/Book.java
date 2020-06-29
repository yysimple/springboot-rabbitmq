package com.jxkj.entity;
/**
 * 不能使用这个导入，会有以下异常
 * No identifier specified for entity: com.jxkj.entity.Book
 */

// import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * @Entity: 注释这是一个jpa方式的实体类，默认生成类名首字母小写的表，这里生成name对应的表名
 */
@Entity
public class Book {

    /**
     * @Id： 表示这个id是主键
     * @GeneratedValue：
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 数据库表中对应的字段名
     */
    @Column(name = "book_name")
    private String name;

    private String author;

    private Float price;
    /**
     * 不在数据库表中生成字段
     */
    @Transient
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
