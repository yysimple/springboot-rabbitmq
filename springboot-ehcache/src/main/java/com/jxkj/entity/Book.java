package com.jxkj.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Book implements Serializable {

    private Integer id;

    private String name;

    private String author;
}
