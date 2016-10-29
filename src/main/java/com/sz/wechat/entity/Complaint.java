package com.sz.wechat.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 投诉反馈数据模型
 * @author sway
 */
public class Complaint implements Serializable {
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
	 * 投诉状态：1-投诉，2-受理，3-处理，4-反馈
	 */
	public static final String DISPOSE_STATUS_TOUSHU="1";
	public static final String DISPOSE_STATUS_SHOULI="2";
	public static final String DISPOSE_STATUS_CHULI="3";
	public static final String DISPOSE_STATUS_FANKUI="4";
	private String disposestatus;
	/**
	 * 投诉内容
	 */
	private String complaincontent;
	/**
	 * 处理时间
	 */
	private String disposetime;
	/**
	 * 处理部门
	 */
	private String disposedep;
	/**
	 * 投诉类型 1-店铺，2-卫生，3-服务,暂时不用
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
	
    /**
     * 投诉图片
     */
    private byte[] complainphoto;

    /**
     * 外联接查询用
     */
    private CompanyInfo companyinfo;
    /**
     * 公司表里的companyname
     */ 
    private String companyName;
    private String evaluate;


	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public byte[] getComplainphoto() {
		return complainphoto;
	}
	public CompanyInfo getCompanyinfo() {
		return companyinfo;
	}
	public void setCompanyinfo(CompanyInfo companyinfo) {
		this.companyinfo = companyinfo;
	}
	public void setComplainphoto(byte[] complainphoto) {
		this.complainphoto = complainphoto;
	}
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}