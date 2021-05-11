package com.jaclon.mistakesOfBuz.concurrenttool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/11 11:54
 */
@Slf4j
public class CopyOnWriteTest {

    public static void main(String[] args) {
        CopyOnWriteTest test = new CopyOnWriteTest();
        //test.testWrite();
        test.testRead();
    }
    public void testWrite(){
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        StopWatch stopWatch = new StopWatch();
        int loopCount = 100000;
        stopWatch.start("Write:copyOnWriteArrayList");
        IntStream.rangeClosed(1,loopCount).parallel().forEach(__ -> copyOnWriteArrayList
                .add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();
        stopWatch.start("Write:synchronizedList");
        IntStream.rangeClosed(1,loopCount).parallel().forEach(__-> synchronizedList
        .add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

    public void testRead(){
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedArrayList = Collections.synchronizedList(new ArrayList<>());
        addAll(copyOnWriteArrayList);
        addAll(synchronizedArrayList);
        StopWatch stopWatch = new StopWatch();
        int loopCount = 1000000;
        int count = copyOnWriteArrayList.size();
        stopWatch.start("Read:copyOnWriteArrayList");
        IntStream.rangeClosed(1,loopCount).parallel().forEach(__->copyOnWriteArrayList
                .get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        stopWatch.start("Read:synchronizedArrayList");
        IntStream.rangeClosed(1,loopCount).parallel().forEach(__->synchronizedArrayList
                .get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }
    private void addAll(List<Integer> list){
        list.addAll(IntStream.rangeClosed(1,1000000).boxed().collect(Collectors.toList()));
    }
}
