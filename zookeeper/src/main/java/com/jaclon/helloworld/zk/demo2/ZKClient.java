package com.jaclon.helloworld.zk.demo2;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/10/28 11:31
 */
public class ZKClient {

    private String connectionUrl = "59.110.153.50:2181";
    private int sessionTimeOut = 3*1000;
    private ZooKeeper zkCli;

    public static void main(String[] args) throws Exception{
        ZKClient zkClient = new ZKClient();
        zkClient.getConnection();
        zkClient.getServers();
        Thread.sleep(Long.MAX_VALUE);
    }

    public void  getServers() throws Exception{
        List<String> children = zkCli.getChildren("/servers", true);
        ArrayList<String> serverList = new ArrayList<>();

        for (String c : children) {
            byte[] data = zkCli.getData("/servers/" + c, true, null);
            serverList.add(new String(data));
        }
        System.out.println(serverList);
    }

    public void getConnection() throws Exception{
        zkCli = new ZooKeeper(connectionUrl, sessionTimeOut, watchedEvent -> {
            List<String> children;
            try {
                children = ZKClient.this.zkCli.getChildren("/servers", true);
                //创建集合存储服务器列表
                ArrayList<String> serverList = new ArrayList<String>();

                //获取每个节点的数据
                for (String c : children) {
                    byte[] data = ZKClient.this.zkCli.getData("/servers/" + c, true, null);
                    serverList.add(new String(data));
                }

                //打印服务器列表
                System.out.println(serverList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        });
    }
}
