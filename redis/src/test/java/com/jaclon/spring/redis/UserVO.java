package com.jaclon.spring.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jaclon
 * @date 2019/9/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    public  static final String Table = "t_user";

    private String name;
    private Integer age;
    private String address;

}
