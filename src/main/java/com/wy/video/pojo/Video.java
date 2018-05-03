package com.wy.video.pojo;
/*
 * 视频类
 */


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wy.category.pojo.Category;

@JsonIgnoreProperties(value={"category","videoState"})
public class Video {
	
	private Integer vid;
	private String name;
	private String intro;   //简介
	private Timestamp uploadtime;  //上传日期
	private String url;     //转码后地址
	private String oriurl;  //转码前地址
	private String thumbnailurl;   //缩略图地址
	private Integer click;
	private Category category;
	private VideoState videoState;
	
	public Video() {
		super();
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Timestamp getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOriurl() {
		return oriurl;
	}

	public void setOriurl(String oriurl) {
		this.oriurl = oriurl;
	}

	public String getThumbnailurl() {
		return thumbnailurl;
	}

	public void setThumbnailurl(String thumbnailurl) {
		this.thumbnailurl = thumbnailurl;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public VideoState getVideoState() {
		return videoState;
	}

	public void setVideoState(VideoState videoState) {
		this.videoState = videoState;
	}
}
