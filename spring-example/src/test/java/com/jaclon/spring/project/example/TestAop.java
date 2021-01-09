package com.jaclon.spring.project.example;

import com.jaclon.spring.project.example.aop3.Dao;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

    @Test
    public void testAop() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("aop.xml");

        Dao dao = (Dao)ac.getBean("daoImpl");
        dao.select();
    }

}