package com.jaclon.mistakesOfBuz.nullValue.pojonotnull;

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
    private Optional<String> name;
    private Optional<Integer> age;
}