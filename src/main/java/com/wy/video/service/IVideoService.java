package com.wy.video.service;

import java.util.List;

import com.wy.crawl.pojo.DownVideo;
import com.wy.utils.PageBean;
import com.wy.video.pojo.Video;

public interface IVideoService {

	List<Video> findNew(int num);

	PageBean<Video> findByCid(Integer cid, Integer page);

	List<Video> findHot(Integer cid);

	Video findByVid(Integer vid);

	void deleteByVid(Video video);

	PageBean<DownVideo> findDownVideoByPage(Integer page);

}
