package com.wy.category.pojo;

/*
 * 一级分类(存放Video)
 */
import java.util.HashSet;
import java.util.Set;

import com.wy.video.pojo.Video;

public class Category {
	private Integer cid;
	private String name;
	//可能需要一个set集合
	private Set<Video> videos = new HashSet<Video>();
	
	public Category() {
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
}
