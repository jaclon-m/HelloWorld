/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.quartzdemo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;

/**
 * @Classname Example1
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/4
 */
public class Example1 {
    public static void main(String[] args) throws SchedulerException {
        StdSchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();

        JobDetail job = newJob(HelloJob.class)
                .withIdentity("myjob","group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).
                                repeatForever()).
                        build();
        sched.scheduleJob(job,trigger);

    }
}

