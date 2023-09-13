package com.jaclon.helloworld.elasticsearch.entity;

import lombok.Data;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2023/9/13 12:11
 */
@Data
public class Account {

    private Long accountNumber;
    private Long balance;
    private String firstname;
    private String lastname;
    private Integer age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}
