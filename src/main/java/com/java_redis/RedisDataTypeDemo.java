package com.java_redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Java操作redis的各种数据结构
 * @author dell
 *
 */
public class RedisDataTypeDemo {
	public static Jedis getJedis(){
		//连接Redis服务器
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println("redis服务器连接成功！");
		return jedis;
	}
	
	/**
	 * Redis的key类型
	 */
	//@Test
	public void redisKey(){
		Jedis jedis = RedisDataTypeDemo.getJedis();
		System.out.println("查看键为mykey的是否存在："+jedis.exists("mykey"));
		System.out.println("查看键为mykey的类型："+jedis.type("mykey"));
		System.out.println("随机获得一个key:"+jedis.randomKey());
		System.out.println("将mykey重命名为mykey1:"+ jedis.rename("mykey", "mykey1"));
		System.out.println("删除key为mykey:"+jedis.del("mykey"));
	}
	
	/**
	 * Redis的String类型
	 */
	//@Test
	public void redisString(){
		Jedis jedis = RedisDataTypeDemo.getJedis(); 
        System.out.println("设置name："+jedis.set("name", "小花"));  
        System.out.println("设置name1："+jedis.set("name1", "小花1"));  
        System.out.println("设置name2："+jedis.set("name2", "小花2"));  
        System.out.println("设置name，如果存在返回0："+jedis.setnx("name", "小花哈哈"));  
        System.out.println("获取key为name和name1的value值："+jedis.mget("name","name1"));  
        System.out.println("自增1："+jedis.incr("index"));  
        System.out.println("自增1："+jedis.incr("index"));  
        System.out.println("自增2："+jedis.incrBy("count", 2));  
        System.out.println("自增2："+jedis.incrBy("count", 2));  
        System.out.println("递减1："+jedis.decr("count"));  
        System.out.println("递减2："+jedis.decrBy("index",2));  
        System.out.println("在name后面添加String："+jedis.append("name", ",我爱你"));  
        System.out.println("获取key为name的值："+jedis.get("name"));  
	}
	
	/**
	 * redis的list类型
	 */
	@Test
	public void redisList(){
		Jedis jedis = RedisDataTypeDemo.getJedis();
		//在列表的头部添加数据
		jedis.lpush("list", "姗姗","age","20","address","beijing");
		//在列表的尾部添加数据
		jedis.rpush("height", "170cm","cupSize","C罩杯");
		//返回长度
		jedis.llen("list");
		//取值
		List<String> list = jedis.lrange("list", 0, -1);
		for(String str : list){
			System.out.println(str);
		}
		//System.out.println("删除key为list的数据"+jedis.del("list"));
		System.out.println("删除key为height的数据"+jedis.del("height"));
	}
	
	/**
	 * redis的set类型
	 */
	//@Test
	public void redisSet(){
		Jedis jedis = RedisDataTypeDemo.getJedis();
		jedis.sadd("city", "北京","上海","广州","深圳");
		System.out.println("取出集合的头部元素：" + jedis.spop("city"));
		System.out.println("随机取出一个值："+ jedis.srandmember("city"));
		jedis.sadd("city2", "昆明","香港","澳门","台湾","上海","北京","成都");
		System.out.println("交集："+jedis.sinter("city","city2"));
		System.out.println("并集："+jedis.sunion("city","city2"));
		System.out.println("差集："+jedis.sdiff("city","city2"));
	}
	
	/**
	 * redis map数据类型
	 */
	//@Test
	public void redisMap(){
		Jedis jedis = RedisDataTypeDemo.getJedis();
		jedis.hset("bigCity", "big", "北京");
		System.out.println("取值：" + jedis.hget("bigCity", "big"));
		Map<String,String> map = new HashMap<String,String>();
		map.put("big1", "上海");
		map.put("big2", "香港");
		map.put("big3", "武汉");
		jedis.hmset("bigCity2", map);
		List<String> list1 = jedis.hmget("bigCity2", "big1","big2","big3");
		for(String str1 : list1){
			System.out.println(str1);
		}
		System.out.println(jedis.hlen("bigCity2"));
	}
	
	public static void main(String[] args) {
		RedisDataTypeDemo redis = new RedisDataTypeDemo();
		//redis.redisKey();
		//redis.redisString();
		//redis.redisList();
		//redis.redisSet();
		//redis.redisMap();
	}

}
