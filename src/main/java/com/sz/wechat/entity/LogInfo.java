package com.sz.wechat.entity;
/**
 * 记录访问信息
 * @author linhd
 *
 */
public class LogInfo {
    
	//用户昵称
	private String nickname;
	//用户对于每一个公众号唯一的标识
	private String openid;
	//性别
	private String sex;
	//语言
	private String language;
	//访问时间
	private String inserttime;
	//访问网页
	private String visitpage;
	//国家
	private String country;
	
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
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
	public String getVisitpage() {
		return visitpage;
	}
	public void setVisitpage(String visitpage) {
		this.visitpage = visitpage;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
}
