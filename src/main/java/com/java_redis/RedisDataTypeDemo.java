package com.java_redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Java����redis�ĸ������ݽṹ
 * @author dell
 *
 */
public class RedisDataTypeDemo {
	public static Jedis getJedis(){
		//����Redis������
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println("redis���������ӳɹ���");
		return jedis;
	}
	
	/**
	 * Redis��key����
	 */
	//@Test
	public void redisKey(){
		Jedis jedis = RedisDataTypeDemo.getJedis();
		System.out.println("�鿴��Ϊmykey���Ƿ���ڣ�"+jedis.exists("mykey"));
		System.out.println("�鿴��Ϊmykey�����ͣ�"+jedis.type("mykey"));
		System.out.println("������һ��key:"+jedis.randomKey());
		System.out.println("��mykey������Ϊmykey1:"+ jedis.rename("mykey", "mykey1"));
		System.out.println("ɾ��keyΪmykey:"+jedis.del("mykey"));
	}
	
	/**
	 * Redis��String����
	 */
	//@Test
	public void redisString(){
		Jedis jedis = RedisDataTypeDemo.getJedis(); 
        System.out.println("����name��"+jedis.set("name", "С��"));  
        System.out.println("����name1��"+jedis.set("name1", "С��1"));  
        System.out.println("����name2��"+jedis.set("name2", "С��2"));  
        System.out.println("����name��������ڷ���0��"+jedis.setnx("name", "С������"));  
        System.out.println("��ȡkeyΪname��name1��valueֵ��"+jedis.mget("name","name1"));  
        System.out.println("����1��"+jedis.incr("index"));  
        System.out.println("����1��"+jedis.incr("index"));  
        System.out.println("����2��"+jedis.incrBy("count", 2));  
        System.out.println("����2��"+jedis.incrBy("count", 2));  
        System.out.println("�ݼ�1��"+jedis.decr("count"));  
        System.out.println("�ݼ�2��"+jedis.decrBy("index",2));  
        System.out.println("��name�������String��"+jedis.append("name", ",�Ұ���"));  
        System.out.println("��ȡkeyΪname��ֵ��"+jedis.get("name"));  
	}
	
	/**
	 * redis��list����
	 */
	@Test
	public void redisList(){
		Jedis jedis = RedisDataTypeDemo.getJedis();
		//���б��ͷ���������
		jedis.lpush("list", "��","age","20","address","beijing");
		//���б��β���������
		jedis.rpush("height", "170cm","cupSize","C�ֱ�");
		//���س���
		jedis.llen("list");
		//ȡֵ
		List<String> list = jedis.lrange("list", 0, -1);
		for(String str : list){
			System.out.println(str);
		}
		//System.out.println("ɾ��keyΪlist������"+jedis.del("list"));
		System.out.println("ɾ��keyΪheight������"+jedis.del("height"));
	}
	
	/**
	 * redis��set����
	 */
	//@Test
	public void redisSet(){
		Jedis jedis = RedisDataTypeDemo.getJedis();
		jedis.sadd("city", "����","�Ϻ�","����","����");
		System.out.println("ȡ�����ϵ�ͷ��Ԫ�أ�" + jedis.spop("city"));
		System.out.println("���ȡ��һ��ֵ��"+ jedis.srandmember("city"));
		jedis.sadd("city2", "����","���","����","̨��","�Ϻ�","����","�ɶ�");
		System.out.println("������"+jedis.sinter("city","city2"));
		System.out.println("������"+jedis.sunion("city","city2"));
		System.out.println("���"+jedis.sdiff("city","city2"));
	}
	
	/**
	 * redis map��������
	 */
	//@Test
	public void redisMap(){
		Jedis jedis = RedisDataTypeDemo.getJedis();
		jedis.hset("bigCity", "big", "����");
		System.out.println("ȡֵ��" + jedis.hget("bigCity", "big"));
		Map<String,String> map = new HashMap<String,String>();
		map.put("big1", "�Ϻ�");
		map.put("big2", "���");
		map.put("big3", "�人");
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
