package com.sz.wechat.basic;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 页面请求地址定位标签类
 * @author sway
 *
 */
public class RquestURI extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	
	/**
	 *	请求地址 
	 */
	private String rquestURI;
	
	
	/**
	 *	标签开始部分，获取请求地址，并重新组装成新的rquestURI
	 */
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request=(HttpServletRequest)super.pageContext.getRequest();
		rquestURI=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		request.setAttribute("base",request.getContextPath());
		return super.doStartTag();
	}
	
	/**
	 *	标签结束部分,将新的请求地址输入到页面
	 */
	@Override
	public int doEndTag() throws JspException {
		JspWriter out=super.pageContext.getOut();
		try {
			out.print("<base href=\""+rquestURI+"\">");
			out.flush();
		} catch (IOException e) {
			System.out.println(e);
		}
		rquestURI=null;
		return super.doEndTag();
	}
	
}
