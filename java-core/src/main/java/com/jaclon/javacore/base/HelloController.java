package com.jaclon.javacore.base;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jaclon
 * @date 2019/10/16
 */
@RestController
@Scope
public class HelloController {


    @RequestMapping("/hello")
    public String HelloWorld(){

        new Thread(()->{
            try {
                int i = 1/0;
            }catch (Exception e){
                System.out.println("============");
                throw new RuntimeException(e);
            }
        }).start();
        return "hello world";
    }

}
