package com.jaclon.mistakesOfBuz.serialization.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/6/7 21:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class User implements Serializable {
    private String name;
    private int age;
}
