package com.jason.java.thread.current;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2022/3/15 11:25
 */
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