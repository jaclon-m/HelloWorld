package com.jaclon.spring.project.example.aop;

import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/2 下午12:27
 */
@Aspect
@Component
public class AuthAspect {
  @Before("execution(* com.jaclon.spring.project.example.bean.*Service.*(..))")
  public void before1(JoinPoint joinPoint) throws AccessDeniedException {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    AuthPermission annotation = method.getAnnotation(AuthPermission.class);
    if(annotation !=null){
      int idx = annotation.idx();
      Object[] args = joinPoint.getArgs();
      int userId = (Integer)args[idx];
      if(userId != 1){
        throw new AccessDeniedException("access denied: " + method.getName());
      }
    }
  }
}
