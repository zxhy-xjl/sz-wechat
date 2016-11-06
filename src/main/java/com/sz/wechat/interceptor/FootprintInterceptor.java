/**
 * 
 */
package com.sz.wechat.interceptor;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.LogInfo;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.LogInfoService;

/**
 * 足迹拦截器
 * @author linhd
 *
 */
public class FootprintInterceptor implements HandlerInterceptor  {
     
	@Autowired
	private LogInfoService logInfoService;
	@Autowired
	private CompanyInfoService companyInfoService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("拦截器准备数据中----");
		System.out.println("请求地址---"+request.getRequestURI()+"；上下文---"+request.getContextPath());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("拦截器运行中------");
		String otherparam = "";
		//Map<String, String[]> paramMap =  request.getParameterMap();
		Enumeration<String>  enum1=request.getParameterNames();  
		       while(enum1.hasMoreElements()){  
		                  String  paramName=(String)enum1.nextElement();                      
		                  String[]  values=request.getParameterValues(paramName);  
		                  for(int  i=0;i<values.length;i++){  
		                	  otherparam =otherparam+("["+i+"]   "+paramName+"  "+values[i]+"\n"); 
		                  } 
		       }
		String companycode = "";
		String companyname = "";
		String chntitle = "";
		if(request.getParameter("companycode")!=null)
		{
			companycode = request.getParameter("companycode");
			companyname = this.companyInfoService.getCompanyByCode(companycode).getCompanyname();
		}
		if(request.getParameter("companyCode")!=null)
		{
			companycode = request.getParameter("companyCode");
			companyname = this.companyInfoService.getCompanyByCode(companycode).getCompanyname();
		}
	    if(modelAndView.getModel().get("chntitle")!=null)
		chntitle=modelAndView.getModel().get("chntitle").toString();
		HttpSession ss = (HttpSession)request.getSession();
		//System.out.println("openid:"+ss.getAttribute("openid"));
		String visiturl = modelAndView.getViewName()+".jsp";
		LogInfo loginfo = new LogInfo();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long timetmp = 1L;
		String time=format.format(date);
		String city = "";
		if(ss.getAttribute("city")!=null)
			city = ss.getAttribute("city").toString();
		String country = "";
		if(ss.getAttribute("country")!=null)
			country = ss.getAttribute("country").toString();
		String headimgurl = "";
		if(ss.getAttribute("headimgurl")!=null)
			headimgurl = ss.getAttribute("headimgurl").toString();
		String language = "";
		if(ss.getAttribute("language")!=null)
			language = ss.getAttribute("language").toString();
		String nickname = "";
		if(ss.getAttribute("nickname")!=null)
			nickname = ss.getAttribute("nickname").toString();
		String province = "";
		if(ss.getAttribute("province")!=null)
		province = ss.getAttribute("province").toString();
		String sex = "";
		if(ss.getAttribute("sex")!=null)
			sex = ss.getAttribute("sex").toString();
		String subscribe_time = "";
		if(ss.getAttribute("subscribe_time")!=null)
			{subscribe_time = ss.getAttribute("subscribe_time").toString();			
			timetmp = Long.parseLong(subscribe_time);
			subscribe_time = format.format(new Date(timetmp*1000L)); 
			}
		String openid = "";
		if(ss.getAttribute("openid")!=null)
			openid = ss.getAttribute("openid").toString();		
		loginfo.setOpenid(openid);
		loginfo.setCity(city);
		loginfo.setCountry(country);
		loginfo.setHeadimgurl(headimgurl);
		loginfo.setInserttime(time);
		loginfo.setLanguage(language);
		loginfo.setNickname(nickname);
		loginfo.setProvince(province);
		loginfo.setSex(sex);
		loginfo.setSubscribe_time(subscribe_time);
		loginfo.setVisitpage(visiturl);
		loginfo.setCompanyname(companyname);
		loginfo.setChntitle(chntitle);
		loginfo.setOtherparam(otherparam);
		this.logInfoService.insertLog(loginfo);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	//System.out.println(request.getParameter("chntitle"));
		
	}
  
	
	
	
	
	
	
}
