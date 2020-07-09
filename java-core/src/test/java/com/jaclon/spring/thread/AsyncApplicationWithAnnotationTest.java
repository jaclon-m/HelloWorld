package com.jaclon.spring.thread;

import com.jaclon.javacore.threadandconcurrent.AsyncApplicationWithAnnotation;
import com.jaclon.javacore.threadandconcurrent.AsyncDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= AsyncApplicationWithAnnotation.class)
public class AsyncApplicationWithAnnotationTest {

    @Autowired
    private AsyncDemo asyncDemo;

    @Test
    public void contextLoads() throws InterruptedException, ExecutionException {
        asyncDemo.asyncInvokeSimplest();
        asyncDemo.asyncInvokeWithParameter("test");
        Future<String> future = asyncDemo.asyncInvokeReturnFuture(1000);
        System.out.println(future.get());
    }

}