package com.jaclon.spring.project.example.aop2.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/4 下午2:14
 */
public class LogArgsAdvice implements MethodBeforeAdvice {
  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    System.out.println("准备执行方法: " + method.getName() + ", 参数列表：" + Arrays.toString(args));
  }

}
