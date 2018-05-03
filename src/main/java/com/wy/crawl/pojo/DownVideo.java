package com.wy.crawl.pojo;

import static javax.persistence.GenerationType.IDENTITY;

public class DownVideo {
	private Integer dvid;
	private String title;
	private String imageURL;
	private String dURL;
	public Integer getDvid() {
		return dvid;
	}
	public void setDvid(Integer dvid) {
		this.dvid = dvid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getdURL() {
		return dURL;
	}
	public void setdURL(String dURL) {
		this.dURL = dURL;
	}
	
	
}
