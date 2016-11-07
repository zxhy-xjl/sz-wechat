package com.sz.wechat.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pojo.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.util.WeixinUtil;

import com.sz.wechat.entity.LogInfo;
import com.sz.wechat.entity.UserInfo;
import com.sz.wechat.service.LogInfoService;
import com.sz.wechat.service.UserInfoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
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
	@Autowired
    private UserInfoService userInfoService;
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userconsole",method = RequestMethod.GET)
	public ModelAndView visitUserGet (HttpServletRequest request, HttpServletResponse response){
		/*String appId = "wx4203dd1ae2c80664";
		// 第三方用户唯一凭证密钥
		String appSecret = "68e330a8bc86b7ddc369e0f3da4a893e";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		String token = at.getToken();
		String requestUrl= "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&next_openid=";
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		//JSONObject jsondata = jsonObject.getJSONObject("data");
		JSONObject dataarray = jsonObject.getJSONObject("data");
		JSONArray openidarray = dataarray.getJSONArray("openid");
		List<UserInfo> userlist = new ArrayList<UserInfo>();
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
		    userlist.add(userinfo);
		}
	    Comparator timecomparator = new Comparator()
	    		{

					@Override
					public int compare(Object o1, Object o2) {
						// TODO Auto-generated method stub
						
						return ( Long.parseLong(((UserInfo) o1).getSubscribe_time()) > Long.parseLong(((UserInfo) o2).getSubscribe_time()) ? -1 :  
           (((UserInfo) o1).getSubscribe_time() ==((UserInfo) o2).getSubscribe_time() ? 0 : 1));  

					}};
					Collections.sort(userlist, timecomparator);
		System.out.println(openidarray.get(0).toString());*/
		 List<UserInfo> userlist = this.userInfoService.getUsers();
		
		ModelAndView modelAndView = new ModelAndView();
        //System.out.println(at.getToken());
		modelAndView.addObject("userlist", userlist);
		modelAndView.setViewName("forward:/manager/userconsole.jsp");
		return modelAndView;
		
	}
	
	
	@RequestMapping(value = "/userdetail",method = RequestMethod.GET)
	public ModelAndView userdetailGet (HttpServletRequest request, HttpServletResponse response){
		String openid = request.getParameter("openid");
		List<LogInfo> loginfolist = this.logInfoService.getLogByOpenid(openid);
		ModelAndView modelAndView = new ModelAndView();
        //System.out.println(at.getToken());
		modelAndView.addObject("loginfolist", loginfolist);
		modelAndView.setViewName("forward:/manager/userdetail.jsp");
		return modelAndView;
		
	}
}
