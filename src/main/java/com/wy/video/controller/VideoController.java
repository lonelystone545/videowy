package com.wy.video.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wy.crawl.pojo.DownVideo;
import com.wy.utils.PageBean;
import com.wy.video.pojo.Video;
import com.wy.video.service.IVideoService;

@Controller
@Scope("prototype")
public class VideoController {
	
	private IVideoService videoService;
	@Autowired
	public void setVideoService(IVideoService videoService) {
		this.videoService = videoService;
	}
	
	@RequestMapping("/video_findByCid")
	public String video_findByCid(Integer cid,Integer page,Model model) {
		PageBean<Video> pageBean = videoService.findByCid(cid,page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("cid", cid);
		
		List<Video> cHlist = videoService.findHot(cid);
		model.addAttribute("cHlist",cHlist);
		return "videolist1";
	}
	
	@RequestMapping("/video_findByVid")
	public String video_findByVid(Integer vid,Model model) {
		Video video= videoService.findByVid(vid);
		model.addAttribute("video", video);
		return "videocontent";
	}
	
	
	@RequestMapping("/video_deleteByVid")
	public ModelAndView video_deleteByVid(Integer vid,HttpSession session,Integer cid) {
		try{
			Video video = videoService.findByVid(vid);
			//相对路径
			String thumbnailPath=video.getThumbnailurl();
			String path=video.getUrl();
			String oripath=video.getOriurl();
			System.out.println(thumbnailPath+path+oripath+"------------");
			//获取根路径（绝对路径）
			String thumbnailrealpath=session.getServletContext().getRealPath("/").replace('\\', '/')
					+thumbnailPath;
			String realpath=session.getServletContext().getRealPath("/").replace('\\', '/')
					+path;
			String orirealpath=session.getServletContext().getRealPath("/").replace('\\', '/')
					+oripath;
			File thumbnailfile=new File(thumbnailrealpath);
			File videofile=new File(realpath);
			File orivideofile=new File(orirealpath);
			
			//删除与之相关的截图文件和视频文件
			if(thumbnailfile!=null){
				thumbnailfile.delete();
			}
			if(videofile!=null){
				videofile.delete();
			}
			if(orivideofile!=null){
				orivideofile.delete();
			}
			//删除数据库中记录
			videoService.deleteByVid(video);
			return new ModelAndView("redirect:/video_findByCid?cid="+cid+"&page="+1);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("error");
		}
	}
	
	@RequestMapping("/video_downvideo")
	public String video_downvideo(Integer page,Model model) {
		PageBean<DownVideo> pageBean = videoService.findDownVideoByPage(page);
		model.addAttribute("pageBean", pageBean);
		return "downvideo";
	}
	
//	@RequestMapping("/video_play")
//	public String video_play(Integer vid,Model model) {
//		Video video = videoService.findByVid(vid);
//		model.addAttribute("video", video);
//		return "videocontent";
//	}
}
