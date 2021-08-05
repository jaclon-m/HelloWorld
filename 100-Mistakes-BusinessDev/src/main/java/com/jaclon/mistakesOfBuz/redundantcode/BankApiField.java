package com.jaclon.mistakesOfBuz.redundantcode;

import java.lang.annotation.*;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/5 20:10
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface BankApiField {
    int order() default -1;

    int length() default -1;

    String type() default "";
}