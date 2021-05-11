package com.jaclon.mistakesOfBuz.concurrenttool;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/11 11:54
 */
@Slf4j
public class ConcurrentHashMapTest {
    //线程个数
    private static int THREAD_COUNT = 10;
    // 总元素数量
    private static int ITEM_COUNT = 1000;
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMapTest test = new ConcurrentHashMapTest();
        test.addNum();
    }

    /**
     *
     * @throws InterruptedException
     */
    public  void addNum() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        log.info("init size:{}",concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1,10).parallel().forEach( i ->{
            //加锁保证多线程环境下只有一段逻辑在执行
            synchronized (concurrentHashMap){
                int gap = ITEM_COUNT - concurrentHashMap.size();
                log.info("gap size: {}",gap);
                concurrentHashMap.putAll(getData(gap));
            }

        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("finish size:{}",concurrentHashMap.size());
    }
    private ConcurrentHashMap<String,Long> getData(int count){
        return LongStream.rangeClosed(1,count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),
                        (o1,o2)-> o1,ConcurrentHashMap::new));
    }

}
