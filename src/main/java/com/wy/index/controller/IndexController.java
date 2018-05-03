package com.wy.index.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wy.category.pojo.Category;
import com.wy.category.service.ICategoryService;
import com.wy.video.pojo.Video;
import com.wy.video.service.IVideoService;




@Controller
@Scope("prototype")
public class IndexController {
	
	private ICategoryService categoryService;
	@Autowired
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	private IVideoService videoService;
	@Autowired
	public void setVideoService(IVideoService videoService) {
		this.videoService = videoService;
	}

	@RequestMapping(value="/index")
	public String index(HttpSession session,Model model) {
		//查询所有分类列表
		List<Category> cList = categoryService.findAll();
//		for(Category c : cList) {
//			System.out.println(c.getName());
//		}
		//将一级分类放入session中
		session.setAttribute("cList",cList);
	
		//显示最新视频num张缩略图
		List<Video> vNlist = videoService.findNew(8);
		model.addAttribute("vNlist", vNlist);
		
		//根据分类id查询对应的热门视频
		//动漫
		List<Video> comicList = videoService.findHot(1);
		model.addAttribute("comicList",comicList);
		//电视剧
		List<Video> tvList = videoService.findHot(2);
		model.addAttribute("tvList",tvList);
		//电影
		List<Video> movieList = videoService.findHot(3);
		model.addAttribute("movieList",movieList);
		//纪录片
		List<Video> docList = videoService.findHot(4);
		model.addAttribute("docList",docList);
		//新闻
		List<Video> newsList = videoService.findHot(5);
		model.addAttribute("newsList",newsList);
		return "index";
	}
	
	@RequestMapping(value="/gotoLogin")
	public String gotoLoin() {
		return "login";
	}
	
	@RequestMapping(value="/gotoRegist")
	public String gotoRegist() {
		return "regist";
	}
	
	@RequestMapping(value="/index1")
	public String goDownload() {
		return "index1";
	}
	
}
