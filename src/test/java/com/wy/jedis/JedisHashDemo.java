package com.wy.jedis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class JedisHashDemo {
	public static void main(String[] args) {
		//构造jedis对象
		Jedis jedis = new Jedis("10.128.2.129",6379);
		Map<String,String> hash = new HashMap<>();
		hash.put("id", "1002");
		hash.put("name", "wy");
		hash.put("age", "25");
		jedis.hmset("user_1002",hash);
		//获取值
		String age = jedis.hget("user_1002","age");
		//获取所有值
		hash = jedis.hgetAll("user_1002");
		
		//关闭连接
		jedis.close();
	}
}
