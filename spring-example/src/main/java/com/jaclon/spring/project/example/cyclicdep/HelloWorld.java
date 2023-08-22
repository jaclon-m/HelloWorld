package com.jaclon.spring.project.example.cyclicdep;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2023/7/18 17:25
 */
public class HelloWorld {
    public static void main(String[] args) {
        // 创建IoC容器，并进行初始化
        String resource = "spring-ioc-circular-dependency.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        // 获取ClassA的实例（此时会发生循环依赖）
        ClassA classA = (ClassA) context.getBean(ClassA.class);
    }
}
