package com.wy.video.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wy.crawl.pojo.DownVideo;
import com.wy.redis.JedisClient;
import com.wy.utils.JsonUtils;
import com.wy.utils.PageBean;
import com.wy.video.dao.IVideoDao;
import com.wy.video.pojo.Video;

@Service
public class VideoServiceImpl implements IVideoService {
	
	private IVideoDao videoDao;
	@Autowired
	public void setVideoDao(IVideoDao videoDao) {
		this.videoDao = videoDao;
	}
	
	@Autowired
	private JedisClient jedisClient;
	
	//注入属性文件中的值
//	@Value("${REDIS_CONTENT_KEY}")
//	private String REDIS_CONTENT_KEY;
	
	/**
	 *  查找最新的num张Video
	 */
	@Override
	public List<Video> findNew(int num) {
		
		//先查缓存
		try {
			//从redis中取缓存数据
			String json = jedisClient.get("REDIS_NEW_KEY");
			if(!StringUtils.isBlank(json)) {  //如果不是空
				//将json转换为list
				List<Video> list = JsonUtils.jsonToList(json, Video.class);
				System.out.println("从缓存中取数据.......");
				return list;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Video> list = videoDao.findNew(num);
		
		//返回结果之前，向缓存中添加数据
		try {
			jedisClient.set("REDIS_NEW_KEY", JsonUtils.objectToJson(list));
			System.out.println("向缓存中添加数据......");
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 根据分类cid查找所有视频
	 */
	@Override
	public PageBean<Video> findByCid(Integer cid,Integer page) {
		PageBean<Video> pageBean = new PageBean<Video>();
		pageBean.setPage(page); //设置当前页数
		int limit = 8;
		pageBean.setLimit(limit);
		int totalCount = videoDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = (int) Math.ceil(totalCount/limit);
		pageBean.setTotalPage(totalPage);
		int begin = (page-1) * limit;
		List<Video> list = videoDao.findByCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据分类id查询对应的热门视频
	 */
	@Override
//	@JsonIgnore
	public List<Video> findHot(Integer cid) {
		//添加缓存
		//查询数据库之前去查询缓存 如果有，直接返回，如果没有，则查询数据库，并在返回结果之前，向缓存中添加数据
		//注意添加缓存时，不应该影响正常业务逻辑，因此使用try catch。如果redis有问题，则仍可以查询数据库
		
		//先查缓存
		try {
			//从redis中取缓存数据
			String json = jedisClient.hget("REDIS_CATEGORY_KEY", cid+"");
			if(!StringUtils.isBlank(json)) {  //如果不是空
				//将json转换为list
				List<Video> list = JsonUtils.jsonToList(json, Video.class);
				System.out.println("从缓存中取出数据....");
				return list;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Video> list = videoDao.findHot(cid);
		for(Video v:list) {
			System.out.println(v.getVid());
		}

//		//返回结果之前，向缓存中添加数据
			try {
//				//为了规范key 可以使用hash（一个key但是有多个项）
//				//定义一个保存内容的key,hash中每一个项item就是cid,值value是list，需要将list转换为json数据。（只有字符串可以存放在redis中，json也是一种字符串）
				jedisClient.hset("REDIS_CATEGORY_KEY", cid+"", JsonUtils.objectToJson(list));
				System.out.println("向缓存中写入数据.....");
			} catch(Exception e) {
				e.printStackTrace();
			}

		return list;
	}

	/**
	 * 根据视频vid查询video
	 */
	@Override
	public Video findByVid(Integer vid) {
		return videoDao.findByVid(vid);
	}

	/**
	 * 根据视频vid删除video
	 */
	@Override
	public void deleteByVid(Video video) {
		//在删除之前，清除缓存中的数据
		Integer vid = video.getVid();
		jedisClient.hdel("REDIS_VIDEO_KEY", vid+"");
		videoDao.deleteByVid(video);
	}

	@Override
	public PageBean findDownVideoByPage(Integer page) {
		PageBean<DownVideo> pageBean = new PageBean<DownVideo>();
		pageBean.setPage(page); //设置当前页数
		int limit = 20;
		pageBean.setLimit(limit);
		int totalCount = videoDao.findDownVideo();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		totalPage = (int) Math.ceil(totalCount/limit);
		pageBean.setTotalPage(totalPage);
		int begin = (page-1) * limit;
		
		//先查缓存
		try {
			//从redis中取缓存数据
			String json = jedisClient.hget("REDI_DOWNVIDEO_KEY", page+"");
			if(!StringUtils.isBlank(json)) {  //如果不是空
				//将json转换为list
				List<DownVideo> list = JsonUtils.jsonToList(json, DownVideo.class);
				pageBean.setList(list);
				System.out.println("从缓存中取数据.......");
				return pageBean;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		List<DownVideo> list = videoDao.findDownVideoByPage(begin,limit);
		
		//返回结果之前，向缓存中添加数据
		try {
			//为了规范key 可以使用hash（一个key但是有多个项）
			//定义一个保存内容的key,hash中每一个项item就是cid,值value是list，需要将list转换为json数据。（只有字符串可以存放在redis中，json也是一种字符串）
			jedisClient.hset("REDI_DOWNVIDEO_KEY", page+"", JsonUtils.objectToJson(list));
			System.out.println("向缓存中添加数据......");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		pageBean.setList(list);
		return pageBean;
	}
}
