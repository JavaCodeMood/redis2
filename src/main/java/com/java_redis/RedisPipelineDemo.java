package com.java_redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Java 操作redis的管道
 * @author dell
 *
 */
public class RedisPipelineDemo {
	public static Jedis getJedis(){
		//连接redis本地服务器
		Jedis jedis = new Jedis("127.0.0.1",6379);  //redis服务器的ip和端口
		System.out.println("本地redis服务器连接成功！");
		return jedis;
	}
	public void hasPipeline(){
		Jedis jedis = RedisPipelineDemo.getJedis();
		System.out.println("开始："+System.currentTimeMillis());
		Pipeline pl = jedis.pipelined();
		for(int i = 0; i< 1000;i++){
			pl.incr("testPL");
		}
		pl.sync();
		System.out.println("结束：" + System.currentTimeMillis());
	}

	public void noPipeline(){
		Jedis jedis = RedisPipelineDemo.getJedis();  
        System.out.println("开始："+System.currentTimeMillis());  
        for(int i=0;i<1000;i++){
        	jedis.incr("testPL");
        }
        System.out.println("结束："+System.currentTimeMillis());  
	}
	
	public static void main(String[] args) {
		RedisPipelineDemo redis = new RedisPipelineDemo();
		//redis.hasPipeline();
		redis.noPipeline();
	}
}
