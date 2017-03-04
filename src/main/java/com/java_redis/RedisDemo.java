package com.java_redis;

import redis.clients.jedis.Jedis;

/**
 * Java����redis
 * @author dell
 *
 */
public class RedisDemo {
	public static Jedis getJedis(){
		Jedis jedis = null;
		jedis = new Jedis("127.0.0.1",6379); //redis��������ip�Ͷ˿�
		//jedis.auth(FinalCollention.PASSWORD);  //����redis������
		return jedis;
	}
	
	public void test(){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			/*
			 Redis Incr ��� key �д��������ֵ��һ��
                               ��� key �����ڣ���ô key ��ֵ���ȱ���ʼ��Ϊ 0 ��Ȼ����ִ�� INCR ������
                               ���ֵ������������ͣ����ַ������͵�ֵ���ܱ�ʾΪ���֣���ô����һ������
                              ��������ֵ������ 64 λ(bit)�з������ֱ�ʾ֮�ڡ�
			 */
			jedis.incr("test");
			long dbsize = jedis.dbSize();
			System.out.println("���ݿ��С��"+dbsize);
			System.out.println("����test��ֵ��"+jedis.get("test"));
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
