package com.sz.wechat.entity;

import java.io.Serializable;

/**
 * 用户信息数据结构
 * @author sway
 *
 */
public class UserInfo implements Serializable  {
 
	private static final long serialVersionUID = 1L;
	/**
	 * 从微信接口获取
	 */
	private String openid;
	/**
	 * 微信号
	 */
	private String userid;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 语言
	 */
	private String language;
	/**
	 * 插入时间
	 */
	private String inserttime;
	/**
	 * 扫一扫类型，1-扫桌、2-扫餐具
	 */
	private String inserttype;
	/**
	 * 用户所在省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 用户关注时间
	 */
	private String subscribe_time;
	/**
	 * 用户头像url
	 */
	private String headimgurl;
	
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getInserttime() {
		return inserttime;
	}
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}
	public String getInserttype() {
		return inserttype;
	}
	public void setInserttype(String inserttype) {
		this.inserttype = inserttype;
	}
}
