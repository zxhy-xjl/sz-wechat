package com.sz.wechat.entity;

import java.io.Serializable;

/**
 * 评分数据结构
 * @author sway
 *
 */
public class Evaluate implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String pid;
	/**
	 * 企业名称
	 */
	private String companycode;
	/**
	 * 分值
	 */
	private String evaluate;
	/**
	 * openid
	 */
	private String openid;
	/**
	 * 创建时间
	 */
	private String createtime;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
