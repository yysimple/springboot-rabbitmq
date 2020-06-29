package com.jxkj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Book {
    private String name;

    private String author;

    //@JsonIgnore
    private Float price;

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private Date publicationData;

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

    public Date getPublicationData() {
        return publicationData;
    }

    public void setPublicationData(Date publicationData) {
        this.publicationData = publicationData;
    }
}
