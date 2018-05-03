package com.wy.jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisTest {
	
	/*
	 * Jedis测试
	 */
	@Test
	public void testJedisSingle() {
		//创建jedis连接对象
		Jedis jedis = new Jedis("10.128.2.129",6379);
		jedis.set("test", "hello");
		String s = jedis.get("test");
		System.out.println(s);
		jedis.close();
	}
	
	/*
	 * jedis连接池
	 */
	@Test
	public void testJedisPool() {
		//构建连接池配置信息
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		//设置最大连接数
		jedisPoolConfig.setMaxTotal(50);
		//构建连接池
		JedisPool pool = new JedisPool(jedisPoolConfig,"10.128.2.129",6379);
		//从连接池中取连接
		Jedis jedis = pool.getResource();
		String s = jedis.get("test");
		System.out.println(s);
		//释放连接返回到连接池中
		pool.returnResource(jedis);
		
		//释放连接池
		pool.close();
	}
	
	
	/*
	 * jedis集群测试
	 */
	@Test
	public void testJedisCluster() throws IOException {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("10.128.2.129", 7001));
		nodes.add(new HostAndPort("10.128.2.129", 7002));
		nodes.add(new HostAndPort("10.128.2.129", 7003));
		nodes.add(new HostAndPort("10.128.2.129", 7004));
		nodes.add(new HostAndPort("10.128.2.129", 7005));
		nodes.add(new HostAndPort("10.128.2.129", 7006));
		//nodes节点ַ
		//jedisCluster对象
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("name","zhang");
		jedisCluster.set("value", "100");
		String name = jedisCluster.get("name");
		String value = jedisCluster.get("value");
		System.out.println(name);
		System.out.println(value);
		
		//关闭jedis集群
		jedisCluster.close();
	}
	
	/*
	 * 在jedis客户端会计算key的hash值，决定存储在哪个节点
	 * 算法：一致性hash
	 */
	public void testShardedJedisPool() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(50);
		
		//定义集群信息
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("10.128.2.129", 7001));
		shards.add(new JedisShardInfo("10.128.2.129", 7002));
		
		//定义集群连接池
		ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, shards);
		ShardedJedis shardedJedis = null;
		
		try{
			//从连接池中获取jedis分片对象
			shardedJedis = shardedJedisPool.getResource();
			for(int i=0;i<100;i++) {
				shardedJedis.set("key"+i, "value"+i);
			}
			//从redis中获取数据
			String value = shardedJedis.get("test");
			System.out.println(value);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//关闭 检测连接是否有效，有效则放回连接池，无效则重置
			if(shardedJedis!=null) {
				shardedJedis.close();
			}
		}
		shardedJedisPool.close();
	}
}
