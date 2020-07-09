package com.jaclon.spring;


import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 *  * 业务需求：新建一个zk实例+会话链接，链接成功后，新建一个znode节点/jaclon值为abcd，获得新建的该节点的值，完成后退出会话结束操作。
 *  * 1	链接session，类似jdbc的connecton
 *  * 2	create + get
 *  * 3	关闭链接
 * @author jaclon
 * @date 2020/7/9
 */
@Slf4j
public class HelloZK {

    private static final String CONNECT_STRING = "192.168.8.3:2181";
    private static final String PATH = "/jaclon";
    private static final int    SESSION_TIMEOUT = 20 * 1000;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        HelloZK hello = new HelloZK();
        //open session
        ZooKeeper zk = hello.startZK();

        if(zk.exists(PATH,false) == null)
        {
            hello.createZnode(zk, PATH, "abcd");
            String result = hello.getZnode(zk, PATH);
            log.info("main(String[]) ----- String result=" + result);
        }else{
            log.info("this node is ok------");
        }
        //close session
        hello.stopZK(zk);
    }

    public ZooKeeper startZK() throws IOException {
        return new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

    public void stopZK(ZooKeeper zk) throws InterruptedException
    {
        if(zk != null)
        {
            zk.close();
        }
    }

    public void createZnode(ZooKeeper zk,String path,String data) throws KeeperException, InterruptedException {
        zk.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public String getZnode(ZooKeeper zk,String path) throws KeeperException, InterruptedException
    {
        String result = null;

        byte[] byteArray = zk.getData(path, false, new Stat());
        result = new String(byteArray);

        return result;
    }
}
