package com.jaclon.helloworld.zk.demo2;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/10/27 20:45
 */
public class TestZk {
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        ZooKeeper zk = new ZooKeeper("59.110.153.50:2181", 30000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("事件：" +watchedEvent.getType() + "\t" + watchedEvent.getPath() + "\t" + watchedEvent.getState());
            }
        });
//        zk.create("/test","漂亮的".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/testRootPath","testRootData".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/testRootPath/testChild","childData".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath",false,null)));
        System.out.println(zk.getChildren("/testRootPath",false,null));

        zk.setData("/testRootPath/testChild","childMoidfy".getBytes(StandardCharsets.UTF_8),-1);
        System.out.println("目录节点状态:" + zk.exists("/testRootPath",true));

        // 创建另外一个子目录节点
        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null)));
        zk.delete("/testRootPath/testChildPathTwo",-1);
        zk.delete("/testRootPath/testChild",-1);
        zk.delete("/testRootPath",-1);
        zk.close();
    }
}
