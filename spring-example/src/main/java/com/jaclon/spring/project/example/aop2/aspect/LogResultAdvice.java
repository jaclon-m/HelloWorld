package com.jaclon.spring.project.example.aop2.aspect;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/4 下午2:15
 */
public class LogResultAdvice implements AfterReturningAdvice {

  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
      throws Throwable {
    System.out.println(method.getName() + "方法返回" + returnValue);
  }
}
