package com.java_redis;

import redis.clients.jedis.Jedis;

/**
 * Java操作redis
 * @author dell
 *
 */
public class RedisDemo {
	public static Jedis getJedis(){
		Jedis jedis = null;
		jedis = new Jedis("127.0.0.1",6379); //redis服务器的ip和端口
		//jedis.auth(FinalCollention.PASSWORD);  //连接redis的密码
		return jedis;
	}
	
	public void test(){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			/*
			 Redis Incr 命令将 key 中储存的数字值增一。
                               如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
                               如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
                              本操作的值限制在 64 位(bit)有符号数字表示之内。
			 */
			jedis.incr("test");
			long dbsize = jedis.dbSize();
			System.out.println("数据库大小："+dbsize);
			System.out.println("测试test的值："+jedis.get("test"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedis.disconnect();
			}
		}
	}
	
	public static void main(String[] args) {
		RedisDemo redis = new RedisDemo();
		redis.test();
	}

}
