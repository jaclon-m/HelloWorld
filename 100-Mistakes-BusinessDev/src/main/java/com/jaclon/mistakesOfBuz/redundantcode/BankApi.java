package com.jaclon.mistakesOfBuz.redundantcode;

import java.lang.annotation.*;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/5 20:09
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface BankApi {
    String desc() default "";

    String url() default "";
}
