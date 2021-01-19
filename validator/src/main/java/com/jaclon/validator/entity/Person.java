package com.jaclon.validator.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * TODO Description
 * @author jaclon
 * @since 2021/1/15 下午3:37
 */
@Data
public class Person {
    @NotNull
    private String name;
    @Max(30)
    private Integer age;
    @Min(1)
    private Integer sex;
}
