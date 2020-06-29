package com.jxkj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class User {
    private String name;

    private Integer age;
}
