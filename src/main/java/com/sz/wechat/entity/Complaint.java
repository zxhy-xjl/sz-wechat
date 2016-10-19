package com.sz.wechat.entity;

import java.io.Serializable;

/**
 * 投诉反馈数据模型
 * @author sway
 */
public class Complaint implements Serializable  {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String pid;
	/**
	 * 企业ID
	 */
	private String companyid;
	/**
	 * 投诉时间
	 */
	private String complaintime;
	/**
	 * 投诉状态：1-收到投诉，2-投诉审核，3-部门处理，4-处理反馈
	 */
	private String disposestatus;
	/**
	 * 投诉内容
	 */
	private String complaincontent;
	/**
	 * 评分
	 */
	private String evaluate	;
	/**
	 * 处理时间
	 */
	private String disposetime;
	/**
	 * 处理部门
	 */
	private String disposedep;
	/**
	 * 投诉类型 1-店铺，2-卫生，3-服务
	 */
	private String complaintype;
	/**
	 *政府处理意见
	 */
	private String disposeresult;
	/**
	 *openid
	 */
	private String openid;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getComplaintime() {
		return complaintime;
	}
	public void setComplaintime(String complaintime) {
		this.complaintime = complaintime;
	}
	public String getDisposestatus() {
		return disposestatus;
	}
	public void setDisposestatus(String disposestatus) {
		this.disposestatus = disposestatus;
	}
	public String getComplaincontent() {
		return complaincontent;
	}
	public void setComplaincontent(String complaincontent) {
		this.complaincontent = complaincontent;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getDisposetime() {
		return disposetime;
	}
	public void setDisposetime(String disposetime) {
		this.disposetime = disposetime;
	}
	public String getDisposedep() {
		return disposedep;
	}
	public void setDisposedep(String disposedep) {
		this.disposedep = disposedep;
	}
	public String getComplaintype() {
		return complaintype;
	}
	public void setComplaintype(String complaintype) {
		this.complaintype = complaintype;
	}
	public String getDisposeresult() {
		return disposeresult;
	}
	public void setDisposeresult(String disposeresult) {
		this.disposeresult = disposeresult;
	}
}