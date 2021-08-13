package com.jaclon.mistakesOfBuz.productionready;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/12 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long userId;
    private String userName;
}