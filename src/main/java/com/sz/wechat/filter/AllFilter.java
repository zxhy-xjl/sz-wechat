package com.sz.wechat.filter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.sz.wechat.dao.LogInfoMapper;
import com.sz.wechat.entity.LogInfo;
import com.sz.wechat.entity.PageInfo;
import com.sz.wechat.service.LogInfoService;
import com.sz.wechat.service.PageInfoService;

/**
 * Servlet Filter implementation class AllFilter
 */
@WebFilter("/AllFilter")
public class AllFilter implements Filter {

	
	@Autowired
	private LogInfoService logInfoService;

    /**
     * Default constructor. 
     */
    public AllFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("过滤器正在被销毁-------");
	}
/*	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{        HttpServletRequest req = (HttpServletRequest)request;   
	HttpServletResponse resp = (HttpServletResponse)response; 
ServletContext sc = req.getsession().getServletContext();  
xmlWebapplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);    
if(cxt != null && cxt.getBean("usersService") != null && usersService == null)  
	usersService = (UsersService) cxt.getBean("usersService");    
Users users = this.usersService.queryByOpenid(openid);
	}*/

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("过滤器正在运行中-------");
		System.out.println(request.getServletContext());
	    HttpServletRequest httpservletreq = (HttpServletRequest) request;
	    //这里无法注入service，所以必须曲线获得
	    ServletContext sc = httpservletreq.getSession().getServletContext(); 
	    XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc); 
	    if(cxt != null && cxt.getBean("logInfoService") != null && logInfoService == null)  
	    	logInfoService = (LogInfoService) cxt.getBean("logInfoService");    
	    System.out.println(httpservletreq.getRequestURI());
	    Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
	    LogInfo loginfo = new LogInfo();
		loginfo.setVisitpage(httpservletreq.getRequestURI());
		loginfo.setChntitle("扫桌主页面");
		loginfo.setCity("");
		loginfo.setCompanyname("");
		loginfo.setCountry("");
		loginfo.setHeadimgurl("");
		loginfo.setInserttime(time);
		loginfo.setLanguage("");
		loginfo.setNickname("");
		loginfo.setOpenid("");
		loginfo.setOtherparam("");
		loginfo.setProvince("");
		loginfo.setSex("");
		loginfo.setSubscribe_time("");
		//logInfoService = new LogInfoService();
		this.logInfoService.insertLog(loginfo);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("过滤器正在初始化-------");
	}

}
