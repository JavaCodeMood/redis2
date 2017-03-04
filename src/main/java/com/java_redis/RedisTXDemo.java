package com.java_redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * redis事务
 * @author dell
 *
 */
public class RedisTXDemo {
	public static Jedis getJedis(){
		//连接redis本地服务器
		Jedis jedis = new Jedis("127.0.0.1", 6379); //redis服务器的ip和端口
		System.out.println("本地redis服务连接成功！");
		return jedis;
	}
	
	//开启事务 没有异常的事务
	public void txDemo(){
		Jedis jedis = getJedis();
		//开启事务
		Transaction tx = jedis.multi();
		
		tx.set("name1", "美女");
		tx.sadd("cupSize", "C罩杯");
		tx.get("name");
		
		List<Object> objs = tx.exec();
		for(Object obj : objs){
			System.out.println(obj.toString());
		}
	}
	
	//开启事务  含有异常的事务
	public void txHasExDemo(){
		Jedis jedis = getJedis();
		//开启事务
		Transaction tx = jedis.multi();
		
		tx.set("txcity", "大理");
		tx.sadd("height", "172");
		int num = 1/0;
		tx.get("txcity");
		List<Object> txHas = tx.exec();
		
		for(Object ob : txHas){
			System.out.println(ob.toString());
		}
	}
	
	public static void main(String[] args) {
		RedisTXDemo redis = new RedisTXDemo();
		//redis.txDemo();
		redis.txHasExDemo();
	}

}
