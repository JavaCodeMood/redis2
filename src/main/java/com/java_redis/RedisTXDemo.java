package com.java_redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * redis����
 * @author dell
 *
 */
public class RedisTXDemo {
	public static Jedis getJedis(){
		//����redis���ط�����
		Jedis jedis = new Jedis("127.0.0.1", 6379); //redis��������ip�Ͷ˿�
		System.out.println("����redis�������ӳɹ���");
		return jedis;
	}
	
	//�������� û���쳣������
	public void txDemo(){
		Jedis jedis = getJedis();
		//��������
		Transaction tx = jedis.multi();
		
		tx.set("name1", "��Ů");
		tx.sadd("cupSize", "C�ֱ�");
		tx.get("name");
		
		List<Object> objs = tx.exec();
		for(Object obj : objs){
			System.out.println(obj.toString());
		}
	}
	
	//��������  �����쳣������
	public void txHasExDemo(){
		Jedis jedis = getJedis();
		//��������
		Transaction tx = jedis.multi();
		
		tx.set("txcity", "����");
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
