/**
 * 编写一个 NIO 群聊系统，实现服务器端和客户端之间的数据简单通讯（非阻塞）
 * 实现多人群聊
 * 服务器端：可以监测用户上线，离线，并实现消息转发功能
 * 客户端：通过 Channel 可以无阻塞发送消息给其它所有用户，同时可以接受其它用户发送的消息（有服务器转发得到）
 *
 * @author jaclon
 * @since 2021/9/5 20:29
 */
package com.jaclon.netty.demo.nio.groupchat;