package com.jaclon.spring.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/10/9 15:38
 */
@Data
public class Account implements Serializable {
    private Integer id;
    private String name;
    private Double money;

}
