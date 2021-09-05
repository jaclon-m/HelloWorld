package com.jaclon.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 1、对操作系统有一定了解的同学，就会大概知道这里监听的是一个Accept通道。这个通道的
 * 作用就是监听，实际建立连接了还会有一个通道。
 * 2、简单说一下为什么。因为客户端发请求的时候，服务器这边是肯定要先有一个监听通道，
 * 监听某个端口是否有客户端要建立链接，如果有客户端想要建立链接，那么会再创建一个和
 * 客户端真正通信的通道。
 * 3、如果有其它客户端还想要建立链接，这个Accept监听端口监听到了，就会再创建几个真正
 * 的通信通道。
 * 4、也就是Server的一个端口可以建立多个TCP连接，因为IP层协议通过
 * 目标地址+端口+源地址+源端口四个信息识别一个上下文
 *
 * @author jaclon
 * @since 2021/9/5 20:10
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(7000));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册后的selectionkey 数量=" + selector.keys().size());

        while (true){
            if(selector.select(1000) == 0){
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys 数量 = " + selectionKeys.size());

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功 生成了一个 socketChannel " + socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接后 ，注册的selectionkey 数量=" + selector.keys().size());
                }

                if(key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("form 客户端 " + new String(buffer.array()));
                }
                keyIterator.remove();
            }
        }
    }
}
