package com.sz.wechat.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sz.wechat.entity.LogInfo;
import com.sz.wechat.entity.UserInfo;
import com.sz.wechat.service.LogInfoService;
import com.sz.wechat.service.UserInfoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 访问日志信息控制层
 * @author linhd
 *
 */
@Controller
public class LogInfoController {

	@Autowired
	private LogInfoService logInfoService;
	

	@ResponseBody
	@RequestMapping(value = "/insertLog",method = RequestMethod.GET)
	public void insertLogPost (HttpServletRequest request, HttpServletResponse response){
	    String json = request.getParameter("json");
	    String url = request.getParameter("url");
	    url = url.replaceAll("\\?.*",""); 
	    //JSONArray jsonArray = JSONArray.fromObject(json);
	    JSONObject jsonObject =JSONObject.fromObject(json);
		LogInfo loginfo =new LogInfo();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		loginfo.setInserttime(time);
		loginfo.setCountry(jsonObject.getString("country"));
		loginfo.setLanguage(jsonObject.getString("language"));
		loginfo.setNickname(jsonObject.getString("nickname"));
		loginfo.setOpenid(jsonObject.getString("openid"));
		loginfo.setSex(jsonObject.getString("sex"));
		loginfo.setVisitpage(url);
		this.logInfoService.insertLog(loginfo);
		
	}
	
}
