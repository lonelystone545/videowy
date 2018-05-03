package com.wy.utils;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper{
	
	private PrintWriter cachedWriter;
	private CharArrayWriter bufferedWriter;
	public ResponseWrapper(HttpServletResponse response) {
		super(response);
		//保存返回结果
		bufferedWriter = new CharArrayWriter();
		//包装printwriter，让所有结果通过这个printwriter写入到bufferedwriter中
		cachedWriter = new PrintWriter(bufferedWriter);
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		return cachedWriter;
	}
	
	public String getResult() {
		return bufferedWriter.toString();
	}
}
