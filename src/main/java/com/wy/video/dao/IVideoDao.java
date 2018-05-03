package com.wy.video.dao;

import java.util.List;

import com.wy.crawl.pojo.DownVideo;
import com.wy.video.pojo.Video;

public interface IVideoDao {
	
	public List<Video> findNew(int num);

	public int findCountByCid(Integer cid);

	public List<Video> findByCid(Integer cid, int begin, int limit);

	public List<Video> findHot(Integer cid);

	public Video findByVid(Integer vid);

	public void deleteByVid(Video video);

	public int findDownVideo();

	public List<DownVideo> findDownVideoByPage(int begin, int limit);
}
