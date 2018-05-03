package com.wy.review.pojo;
/*
 * 评论类
 */

import java.sql.Timestamp;

import com.wy.user.pojo.User;
import com.wy.video.pojo.Video;

public class Review {
	private Integer rid;
	private Timestamp time;
	private String content;
	private Video video;
	private User user;
	
	public Review() {
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
