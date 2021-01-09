package com.jaclon.helloworld.zk;

import org.apache.log4j.Logger;
import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 题目：多次触发监控，交替打出新值和老值的数据。
 * @author zhouyang
 * @version 创建时间：2017年9月13日  下午4:19:24
 */
public class WatchMore
{
	/**
	* Logger for this class
	*/
	private static final Logger logger = Logger.getLogger(WatchMore.class);
	
	private static final String CONNECT_STRING = "192.168.10.167:2181";
	private static final String PATH = "/atguigu";
	private static final int    SESSION_TIMEOUT = 20 * 1000; 
	
	private ZooKeeper zk = null;
	private String    oldValue = null;
	
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
		oldValue = result;
		logger.info("-------------oldValue: "+oldValue);
		return result;
	}
	
	public boolean triggerValue(String path) throws KeeperException, InterruptedException
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
		
		String newValue = result;
		
		if(oldValue.equals(newValue))
		{
			logger.info("no change--------------");
			return false;
		}else{
			logger.info("-----oldValue: "+oldValue+"\t newValue: "+newValue);
			oldValue = newValue;
			return true;
		}

	}
	
	
	public static void main(String[] args) throws Exception
	{
		WatchMore watchMore = new WatchMore();
		watchMore.setZk(watchMore.startZK());
		
		if(watchMore.getZk().exists(PATH, false) == null)
		{
			watchMore.createZnode(PATH, "AAA");
			
			String result = watchMore.getZnode(PATH);
			
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

	public String getOldValue()
	{
		return oldValue;
	}

	public void setOldValue(String oldValue)
	{
		this.oldValue = oldValue;
	}
	
	
	
}
