package com.jaclon.spring.project.example.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/2 下午12:28
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface AuthPermission {

  /**
   * 0 ,-1表示无法校验
   * @return
   */
  int idx() default 0;
}
