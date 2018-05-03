package com.wy.user.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wy.user.pojo.User;
import com.wy.user.service.IUserService;

@Controller
@Scope("prototype")
public class UserController {
	
	private IUserService userService;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/login")
	public ModelAndView login(User user,String gotoUrl,HttpSession session,HttpServletResponse response) {
		//数据库中查询
		ModelAndView mv = new ModelAndView();
		User existUser = userService.findByUserName(user.getUsername());
		if(existUser!=null && existUser.getPassword().equals(user.getPassword())) {
			//把user存放到session中
			session.setAttribute("existUser", existUser);
			/*
			 * 单点登录实现
			 */
//			Cookie cookie = new Cookie("ssocookie","sso");
			//在localhost域下的所有应用都可以访问
//			cookie.setPath("/");
//			response.addCookie(cookie);
			mv.setViewName("index");
//			mv.setViewName(gotoUrl);
		} else {
			mv.setViewName("login");
			//这里用代码提示错误的信息
			//在jsp页面上回显调用controller中的信息，使用modelAndView或者ModelMap传递
			mv.addObject("loginError", "用户名或者密码错误");
		}
		return mv;
	}
	
	@RequestMapping(value="/regist")
	public String regist(User user,HttpSession session,Model model,String checkcode) {
		
		System.out.println(session.getAttribute("checkcode"));
		System.out.println(checkcode);
		//判断输入的验证码是否正确
		if(checkcode.equalsIgnoreCase((String)session.getAttribute("checkcode"))){
			//向数据库中添加数据
			userService.save(user);
			return "index";
		} else {
			model.addAttribute("codeError", "验证码输入错误");
			return "regist";
		}
	}
	
	@RequestMapping("/user_findByUserName")
	//根据用户名查询用户
	public String user_findByUserName(User user,HttpServletResponse response) throws IOException {
		System.out.println(user.getUsername());
		user = userService.findByUserName(user.getUsername());
		response.setContentType("text/html;charset=UTF-8");
		if(user!=null) {
			response.getWriter().println("用户名已存在");
		} else {
			response.getWriter().print("ok");
		}
		return null;
	}
	
	@RequestMapping("/user_quit")
	public String user_quit(HttpSession session) {
		session.removeAttribute("existUser");
		return "index";
	}
}
