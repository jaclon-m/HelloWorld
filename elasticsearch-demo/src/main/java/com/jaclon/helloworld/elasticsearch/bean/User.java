package com.jaclon.helloworld.elasticsearch.bean;

import lombok.Data;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2022/3/21 09:33
 */
@Data
public class User {
    private Integer id;
    private Integer age;
    private String userName;
    private String gender;
}
