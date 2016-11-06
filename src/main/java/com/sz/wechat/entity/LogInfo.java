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
	//城市
	private String city;
	//省份
	private String province;
	//用户头像url
	private String headimgurl;
	//最近一次关注时间
	private String subscribe_time;
	//访问页面名字
	private String chntitle;
	//访问餐厅名称
	private String companyname;
	//其他参数
	private String otherparam;
	
	
	
	public String getChntitle() {
		return chntitle;
	}
	public void setChntitle(String chntitle) {
		this.chntitle = chntitle;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getOtherparam() {
		return otherparam;
	}
	public void setOtherparam(String otherparam) {
		this.otherparam = otherparam;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
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
