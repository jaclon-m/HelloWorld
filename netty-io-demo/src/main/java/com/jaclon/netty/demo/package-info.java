/**
 * JDK7 引入了 AsynchronousI/O，即 AIO。在进行 I/O 编程中，常用到两种模式：Reactor 和 Proactor。
 * Java 的 NIO 就是 Reactor，当有事件触发时，服务器端得到通知，进行相应的处理
 * AIO 即 NIO2.0，叫做异步不阻塞的 IO。AIO 引入异步通道的概念，采用了 Proactor 模式，简化了程序编写，有效的请求才启动线程，
 * 它的特点是先由操作系统完成后才通知服务端程序启动线程去处理，一般适用于连接数较多且连接时间较长的应用
 * 目前 AIO 还没有广泛应用，Netty 也是基于 NIO，而不是 AIO，因此我们就不详解 AIO 了，有兴趣的同学可以参考《Java新一代网络编程模型AIO原理及Linux系统AIO介绍》
 *
 * 代码 https://github.com/dongzl/netty-handbook
 * @author jaclon
 * @since 2021/9/1 18:40
 */
package com.jaclon.netty.demo;