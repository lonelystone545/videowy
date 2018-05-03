package com.wy.video.pojo;

import java.util.HashSet;
import java.util.Set;

public class VideoState {
	private Integer vsid;
	private String name;
	private Integer order;
	private String cssstyle;
	private Set<Video> videos = new HashSet<Video>();
	
	public VideoState() {
		super();
	}
	public Integer getVsid() {
		return vsid;
	}
	public void setVsid(Integer vsid) {
		this.vsid = vsid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getCssstyle() {
		return cssstyle;
	}
	public void setCssstyle(String cssstyle) {
		this.cssstyle = cssstyle;
	}
	public Set<Video> getVideos() {
		return videos;
	}
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
	
}
