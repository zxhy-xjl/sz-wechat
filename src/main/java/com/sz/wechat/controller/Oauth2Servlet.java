package com.sz.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.WeixinUtil;

import net.sf.json.JSONObject;
/**
 * 微信授权获取用户信息
 * @author lenovo
 *
 */
@Controller
public class Oauth2Servlet {
	
	@ResponseBody
	@RequestMapping(value = "/Oauth2Servlet")
	public void authGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
			// 第三方用户唯一凭证
			String appId = "wx4203dd1ae2c80664";
			// 第三方用户唯一凭证密钥
			String appSecret = "68e330a8bc86b7ddc369e0f3da4a893e";
	        //静默授权
	        String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
	                + "appid="
	                + appId
	                + "&secret="
	                + appSecret
	                + "&code=CODE&grant_type=authorization_code";

	        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        String code = request.getParameter("code");

	        System.out.println("******************code=" + code);
	       
	        get_access_token_url = get_access_token_url.replace("CODE", code);
	        JSONObject jsonObject = WeixinUtil.httpRequest(get_access_token_url, "GET", null);
	        
	        
	        //String json = HttpsGetUtil.doHttpsGetJson(get_access_token_url);

	        //JSONObject jsonObject = JSONObject.fromObject(json);
	        String openid = jsonObject.getString("openid");
	        String newtoken = jsonObject.getString("access_token");
            String userinfourl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            userinfourl = userinfourl.replace("ACCESS_TOKEN", newtoken);
            userinfourl = userinfourl.replace("OPENID", openid);
	        JSONObject userinfoObject = WeixinUtil.httpRequest(userinfourl, "GET", null);

            
	        response.setContentType("text/html; charset=utf-8");
	        HttpSession ss = (HttpSession)request.getSession();
	        ss.setAttribute("openid",userinfoObject.get("openid")); 
	        ss.setAttribute("nickname",userinfoObject.get("nickname")); 
	        ss.setAttribute("sex",userinfoObject.get("sex")); 
	        ss.setAttribute("province",userinfoObject.get("province")); 
	        ss.setAttribute("city",userinfoObject.get("city")); 
	        ss.setAttribute("headimgurl",userinfoObject.get("headimgurl")); 
	        ss.setAttribute("country",userinfoObject.get("country")); 
	        ss.setAttribute("subscribe_time",userinfoObject.get("subscribe_time")); 
	        
	        
	        PrintWriter out = response.getWriter();
	   /*     out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
	        out.println("<HTML>");
	        out.println("  <HEAD><TITLE>我的</TITLE></HEAD>");
	        out.println("  <BODY>");
	        out.print("    This is ");
	        out.print(this.getClass());
	        out.println(",\n"+jsonObject.toString());
	        out.println("openid:" + openid + "and\n"+userinfoObject.toString());
	        out.println(">");
	        out.println("  </BODY>");
	        out.println("</HTML>");*/
	        out.println(userinfoObject);
	        out.flush();
	        out.close();
	    }

}
