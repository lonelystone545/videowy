package com.wy.jedis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wy.redis.JedisClient;
import com.wy.utils.JsonUtils;
import com.wy.video.pojo.Video;
import com.wy.video.service.IVideoService;

/*
 * spring单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class Jedis_SpringTest {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private IVideoService videoService;
	
	
	
	@Test
	public void testJedis() {
		List<Video> list = videoService.findHot(1);
		
		System.out.println(list.get(0));
	}
	
	@Test
	public void testJedisClientSpring() {
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		
		jedisClient.set("client2", "1000");
		String value = jedisClient.get("client2");
		System.out.println(value);
		
		List<Video> list = new ArrayList<Video>();
		Video video = new Video();
		video.setName("wy");
		video.setClick(1000);
		video.setOriurl("qweidfksdfhskd");
		list.add(video);
		try {
			jedisClient.hset("REDIS_CONTENT_KEY1", "1", JsonUtils.objectToJson(list));
			String json = jedisClient.hget("REDIS_CONTENT_KEY1", "1");
			System.out.println(json);
			list = JsonUtils.jsonToList(json, Video.class);
			System.out.println(list);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
