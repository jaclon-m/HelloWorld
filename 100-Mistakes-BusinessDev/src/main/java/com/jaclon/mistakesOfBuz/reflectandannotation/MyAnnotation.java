package com.jaclon.mistakesOfBuz.reflectandannotation;

import java.lang.annotation.*;

/**
 *
 * @author jaclon
 * @since 2021/8/4 18:25
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotation {
    String value();
}
