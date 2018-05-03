package com.wy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wy.redis.JedisClientSingle;
import com.wy.utils.ResponseWrapper;

public class CacheFilter implements Filter,ApplicationContextAware{
	
	private static ApplicationContext ctx;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//如果不是主页，则放行
		if(req.getRequestURI().equals("/videowy/index")==false) {
			chain.doFilter(request, response);
			return;
		}
		//访问的是主页
		String html = getHtmlFromCache();
		if(html==null) {
			ResponseWrapper wrapper = new ResponseWrapper(res);
			//以上代码在请求被处理之前执行
			
			chain.doFilter(request, wrapper);
			
			//以下代码在请求被处理之后执行
			
			//存入缓存
			html = wrapper.getResult();
			putIntoCache(html);
		}
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().print(html);
	}

	private void putIntoCache(String html) {
		try {
			JedisClientSingle jedis = (JedisClientSingle) ctx.getBean(JedisClientSingle.class);
			jedis.set("home", html);
			jedis.expire("home", 600);
			System.out.println("将首页存入缓存......");
		} catch(Exception e) {
			e.printStackTrace();
		}
//		StringRedisTemplate redis = (StringRedisTemplate)ctx.getBean("redisTemplate");
//		redis.opsForValue().set("home", html,TimeUnit.MINUTES.toSeconds(1));
	}

	private String getHtmlFromCache() {
//		StringRedisTemplate redis = (StringRedisTemplate)ctx.getBean("redisTemplate");
		String html = null;
		try {
			JedisClientSingle jedis = (JedisClientSingle) ctx.getBean(JedisClientSingle.class);
//		return redis.opsForValue().get("home");
			html = jedis.get("home");
			System.out.println("从缓存中取出首页.....");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return html;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}
}
