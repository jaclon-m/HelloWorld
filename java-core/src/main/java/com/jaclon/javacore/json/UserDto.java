package com.jaclon.javacore.json;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/28 17:40
 */
import lombok.Data;

import java.util.Optional;

@Data
public class UserDto {
    private Long id;
    private String name;
    private Integer age;
}