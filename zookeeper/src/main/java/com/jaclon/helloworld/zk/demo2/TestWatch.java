package com.jaclon.helloworld.zk.demo2;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/10/28 11:24
 */
public class TestWatch {
    static List<String> children  = null;

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("59.110.153.50:2181", 30000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("正在监听中");
            }
        });

        children = zk.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("监听路径为：" + event.getPath());
                System.out.println("监听的类型为：" + event.getType());
                System.out.println("数据被2货修改了！！！");

                for (String c : children) {
                    System.out.println(c);
                }
            }
        });

        byte[] data = zk.getData("/temp-p", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("监听路径为：" + event.getPath());
                System.out.println("监听的类型为：" + event.getType());
                System.out.println("数据被2货修改了！！！");
            }
        }, null);
        System.out.println(new String(data));

        Thread.sleep(Long.MAX_VALUE);
    }
}
