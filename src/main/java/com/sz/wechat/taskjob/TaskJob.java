package com.sz.wechat.taskjob;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import org.pojo.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.util.WeixinUtil;

import com.sz.wechat.entity.UserInfo;
import com.sz.wechat.service.UserInfoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("taskJob")
public class TaskJob {
	
	@Autowired
	private UserInfoService userInfoService;
	
	
    @Scheduled(cron = "0 0 22 ? * *")
	public void job1()
	{
    	System.out.println("定时器运行准备开始——+++++++++++");
		String appId = "wx4203dd1ae2c80664";
		// 第三方用户唯一凭证密钥
		String appSecret = "68e330a8bc86b7ddc369e0f3da4a893e";
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		String token = at.getToken();
		String requestUrl= "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&next_openid=";
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		JSONObject dataarray = jsonObject.getJSONObject("data");
		JSONArray openidarray = dataarray.getJSONArray("openid");
		String openidUrl = "";
		JSONObject openidObject = new JSONObject();
		Long time = 1L;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    String subtime = "";
		for(int i=0;i<openidarray.size();i++)
		{	UserInfo userinfo = new UserInfo();
		    openidUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openidarray.getString(i)+"&lang=zh_CN";
		    openidObject = WeixinUtil.httpRequest(openidUrl, "GET", null);
		    userinfo.setOpenid(openidObject.getString("openid"));
		    userinfo.setSex(openidObject.getString("sex"));
		    userinfo.setNickname(openidObject.getString("nickname"));
		    userinfo.setCity(openidObject.getString("city"));
		    userinfo.setProvince(openidObject.getString("province"));
		    userinfo.setHeadimgurl(openidObject.getString("headimgurl"));
		    time = Long.parseLong(openidObject.getString("subscribe_time"));
		    subtime = sdf.format(new Date(time*1000L)); 
		    userinfo.setSubscribe_time(subtime);
		    if(this.userInfoService.getUsersByOpenid(userinfo.getOpenid())==null)
		    {
		    	this.userInfoService.insertUserInfo(userinfo);
		    }else
		    {
		    	this.userInfoService.updateUserInfo(userinfo);
		    }
		    
		}
    	
		System.out.println("定时器运行业已结束——+++++++++++");
		
	}
	
}
