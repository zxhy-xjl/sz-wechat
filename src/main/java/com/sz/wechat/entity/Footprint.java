package com.sz.wechat.entity;
/**
 * 足迹实体类
 * @author linhd
 *
 */
public class Footprint {
    
	//主键
	private String pid;
	//用户对公众号唯一标识
	private String openid;
	//公司唯一编码
	private String companycode;
	//餐饮评分
	private String score;
	//是否存在投诉标识 1-有 2-无
	private String complaintflag;
	//访问时间
	private String visittime;
	//付款状态  1-已付款 2-未付款
	private String paystatus;
    //企业名称
	private String companyname;
	public String getPid() {
		return pid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getComplaintflag() {
		return complaintflag;
	}

	public void setComplaintflag(String complaintflag) {
		this.complaintflag = complaintflag;
	}

	public String getVisittime() {
		return visittime;
	}

	public void setVisittime(String visittime) {
		this.visittime = visittime;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}
	
	
	
	
	
	
	
	
	
	
}
