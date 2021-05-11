package com.jaclon.mistakesOfBuz.concurrenttool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 使用 ConcurrentHashMap 来统计，Key 的范围是 10。
 * 使用最多 10 个并发，循环操作 1000 万次，每次操作累加随机的 Key。
 * 如果 Key 不存在的话，首次设置值为 1。
 *
 * @author jaclon
 * @since 2021/5/11 11:54
 */
@Slf4j
public class ConcurrentHashMapTest2 {
    //循环次数
    private static int LOOP_COUNT = 10000000;
    //线程数量
    private static int THREAD_COUNT = 10;
    // 元素数量
    private static int ITEM_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMapTest2 test = new ConcurrentHashMapTest2();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normalUse");
        Map<String, Long> normalUse = test.normalUse();
        stopWatch.stop();
        Assert.isTrue(normalUse.size() == ITEM_COUNT,"normalUse size error");
        Assert.isTrue(normalUse.entrySet().stream().mapToLong(item -> item.getValue()).reduce(0,Long::sum) == LOOP_COUNT
        ,"normalUse count error");

        stopWatch.start("goodUse");
        Map<String, Long> goodUse = test.goodUse();
        stopWatch.stop();
        Assert.isTrue(goodUse.size() == ITEM_COUNT,"goodUse size error");
        Assert.isTrue(goodUse.entrySet().stream()
        .mapToLong(item-> item.getValue()).reduce(0,Long::sum) == LOOP_COUNT
        ,"goodUse count error");
        log.info(stopWatch.prettyPrint());
    }
    private Map<String,Long> normalUse() throws InterruptedException {
        ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1,LOOP_COUNT).parallel().forEach( i->{
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            synchronized (freqs){
                if(freqs.containsKey(key)){
                    freqs.put(key,freqs.get(key)+1);
                }else {
                    freqs.put(key,1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1,TimeUnit.HOURS);
        return freqs;
    }

    private Map<String,Long> goodUse() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1,LOOP_COUNT).parallel().forEach( i->{
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            freqs.computeIfAbsent(key,k->new LongAdder()).increment();
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1,TimeUnit.HOURS);
        return freqs.entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(),e->e.getValue().longValue())
        );
    }

}
