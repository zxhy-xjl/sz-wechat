package com.sz.wechat.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 运行模式，主要用设置是调试还是正式运行
 * @author leasonlive
 *
 */
@Component
public class RuntimeModel {
	private boolean localDevModel=false;
	private String localOpenId="oehpaw8_fgOEWtPk0S0gLidH60xg";
	public String getOpenId(HttpServletRequest request){
		if (localDevModel){
			return this.localOpenId;
		}
		
		String openId = request.getParameter("openid");
		if (StringUtils.isBlank(openId)){
			return this.getOpenId(request.getSession());
		} else {
			return openId;
		}
	}
	private String getOpenId(HttpSession ss){
		if (localDevModel){
			return this.localOpenId;
		}
		return ss.getAttribute("openid").toString();
	}
	public boolean isLocalDevModel() {
		return localDevModel;
	}
	public void setLocalDevModel(boolean localDevModel) {
		this.localDevModel = localDevModel;
	}
	public String getLocalOpenId() {
		return localOpenId;
	}
	public void setLocalOpenId(String localOpenId) {
		this.localOpenId = localOpenId;
	}
	
}
