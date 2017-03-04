package com.java_redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Java ����redis�Ĺܵ�
 * @author dell
 *
 */
public class RedisPipelineDemo {
	public static Jedis getJedis(){
		//����redis���ط�����
		Jedis jedis = new Jedis("127.0.0.1",6379);  //redis��������ip�Ͷ˿�
		System.out.println("����redis���������ӳɹ���");
		return jedis;
	}
	public void hasPipeline(){
		Jedis jedis = RedisPipelineDemo.getJedis();
		System.out.println("��ʼ��"+System.currentTimeMillis());
		Pipeline pl = jedis.pipelined();
		for(int i = 0; i< 1000;i++){
			pl.incr("testPL");
		}
		pl.sync();
		System.out.println("������" + System.currentTimeMillis());
	}

	public void noPipeline(){
		Jedis jedis = RedisPipelineDemo.getJedis();  
        System.out.println("��ʼ��"+System.currentTimeMillis());  
        for(int i=0;i<1000;i++){
        	jedis.incr("testPL");
        }
        System.out.println("������"+System.currentTimeMillis());  
	}
	
	public static void main(String[] args) {
		RedisPipelineDemo redis = new RedisPipelineDemo();
		//redis.hasPipeline();
		redis.noPipeline();
	}
}
