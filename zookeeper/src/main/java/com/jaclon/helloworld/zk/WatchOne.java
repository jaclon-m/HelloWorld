package com.jaclon.helloworld.zk;

import org.apache.log4j.Logger;
import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 
 * 一次watch监控触发
 * 1	创建/atguigu节点并设置为AAA
 * 2	获得上一步的值AAA，获得成功后设置一个观察者，监控/atguigu的值是否发生了变化
 * 3	假如/atguigu节点的值发生了变化，比如变为了BBB，要求能够获得/atguigu节点的最新值BBB
 * @author zhouyang
 * @version 创建时间：2017年9月13日  下午3:57:19
 */
public class WatchOne
{
	/**
	* Logger for this class
	*/
	private static final Logger logger = Logger.getLogger(WatchOne.class);
	
	private static final String CONNECT_STRING = "192.168.10.167:2181";
	private static final String PATH = "/atguigu";
	private static final int    SESSION_TIMEOUT = 20 * 1000; 
	
	private ZooKeeper zk = null;
	
	public ZooKeeper startZK() throws IOException
	{
		return new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT,new Watcher() {
			@Override
			public void process(WatchedEvent event)
			{
			}
		});
	}
		
	public void createZnode(String path,String data) throws KeeperException, InterruptedException
	{
		zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	public String getZnode(String path) throws KeeperException, InterruptedException
	{
		String result = null;
		
		byte[] byteArray = zk.getData(path, new Watcher() {
			@Override
			public void process(WatchedEvent event)
			{
				try 
				{
					triggerValue(path);
				}catch (KeeperException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, new Stat());
		result = new String(byteArray);
		
		return result;
	}
	
	public String triggerValue(String path) throws KeeperException, InterruptedException
	{
		String result = null;
		
		byte[] byteArray = zk.getData(path, false, new Stat());
		result = new String(byteArray);
		
		logger.info("triggerValue(String) ---------result" + result);//BBB
		
		return result;	
	}
	
	
	public static void main(String[] args) throws Exception
	{
		WatchOne watchOne = new WatchOne();
		watchOne.setZk(watchOne.startZK());
		
		if(watchOne.getZk().exists(PATH, false) == null)
		{
			watchOne.createZnode(PATH, "AAA");
			
			String result = watchOne.getZnode(PATH);
			
			logger.info("main() ---------result" + result);//AAA
			
			Thread.sleep(Long.MAX_VALUE);
		}else{
			logger.info("main() ---------this node is ok");
		}
	}



	
	//setter----getter
	public ZooKeeper getZk()
	{
		return zk;
	}
	public void setZk(ZooKeeper zk)
	{
		this.zk = zk;
	}
	
	
	
}
