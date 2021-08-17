package com.jaclon.mistakesOfBuz.addmet;

/**
 * 问题定位
 *
 * 主机层面，对 CPU、内存、磁盘、网络等资源做监控
 * 网络层面，需要监控专线带宽、交换机基本情况、网络延迟。
 * 所有的中间件和存储都要做好监控，不仅仅是监控进程对 CPU、内存、磁盘 IO、网络使 用的基本指标，更重要的是监控组件内部的一些重要指标。
 * 比如，著名的监控工具 Prometheus，就提供了大量的  exporter来对接各种中间件和存储系统。
 * 应用层面，需要监控 JVM 进程的类加载、内存、GC、线程等常见指标(比如使用 Micrometer来做应用监控)，此外还要确保能够收集、保存应用日志、GC 日志。
 * @author jaclon
 * @since 2021/8/17 15:13
 */
public class LocateProblem {
    /**
     * 主机层面的问题，可以使用工具排查:
     * CPU 相关问题，可以使用 top、vmstat、pidstat、ps 等工具排查;
     * 内存相关问题，可以使用 free、top、ps、vmstat、cachestat、sar 等工具排查;
     * IO 相关问题，可以使用 lsof、iostat、pidstat、sar、iotop、df、du 等工具排查;
     * 网络相关问题，可以使用 ifconfig、ip、nslookup、dig、ping、tcpdump、iptables 等工具排查。
     */

    /**
     * 对于 CPU 使用高的问题，如果现场还在，具体的分析流程是:
     * 首先，在 Linux 服务器上运行 top -Hp pid 命令，来查看进程中哪个线程 CPU 使用 高;
     * 然后，输入大写的 P 将线程按照 CPU 使用率排序，并把明显占用 CPU 的线程 ID 转换 为 16 进制;
     * 最后，在 jstack 命令输出的线程栈中搜索这个线程 ID，定位出问题的线程当时的调用 栈。
     *
     * 如果没有条件直接在服务器上运行 top 命令的话，我们可以用采样的方式定位问题:
     * 间隔 固定秒数(比如 10 秒)运行一次 jstack 命令，采样几次后，对比采样得出哪些线程始终 处于运行状态，分析出问题的线程。
     */

    /**
     * 我们线上k8s管理服务，有时候oom，服务重启由k8s触发的，这将导致设置的生成dump 文件无效。有好的思路吗
     *
     * : 1、需要明白，xmx设置的堆只是java进程使用内存的一部分 https://stackoverflow.co m/questions/53451103/java-using-much-more-memory-than-heap-size-or-size-correctly -docker-memory-limi
     * 所以你需要通过监控排查到底哪部分内存超限，但是heap的oom dump肯定是需要做的，
     * 并且配 置-XX:NativeMemoryTracking=detail -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTS tatistics，
     * 需要的时候可以通过jcmd <pid> VM.native_memory summary/detail排查
     * 2、在排查清问题之前可以适当放开k8s的limit，以便你可以观察到内存增长的区域，方便排查问 题
     * 3、可以和运维沟通一下，在oom killed的时候(oom killed应该会让pod状态变为unhealth，这 个时候可以触发hook)能否做一下heapdump、jstack等，数据不要保存在容器里
     *
     * :k8s应该在cpu或者内存的 使用率上做报警，大于90%的时候可以dump和jstack一次，甚至jstat也可以做，
     * 然后95% 的时候也同样执行一次， 甚至98或者99的时候也可以做一次，这样不仅可以保留现场，同时还可以对比。可以更好 的排查问题。...
     */
}
