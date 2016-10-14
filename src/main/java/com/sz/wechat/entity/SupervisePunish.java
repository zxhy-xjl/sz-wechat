package com.sz.wechat.entity;

import java.io.Serializable;

/**
 * 监管处罚得分
 * @author sway
 *
 */
public class SupervisePunish implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 行政处罚决定书文号
	 */
	private String adminpenaltybook;
	/**
	 * 案件名称
	 */
	private String casename;
	/**
	 * 违法行为类型
	 */
	private String unlawfulact;
	/**
	 * 违法企业名称或违法自然人姓名
	 */
	private String nlawfulcompanyname;
	/**
	 * 法定代表人姓名
	 */
	private String legalperson;
	/**
	 * 主要违法事实
	 */
	private String unlawfulfact;
	/**
	 * 处罚种类和方式
	 */
	private String penaltytype;
	/**
	 * 行政处罚的履行方式和期限
	 */
	private String adminpenaltytypeandtime;
	/**
	 * 作出处罚的机关名称（全称）
	 */
	private String penaltyoffice;
	/**
	 * 处罚日期
	 */
	private String punishmentdate;
	public String getAdminpenaltybook() {
		return adminpenaltybook;
	}
	public void setAdminpenaltybook(String adminpenaltybook) {
		this.adminpenaltybook = adminpenaltybook;
	}
	public String getCasename() {
		return casename;
	}
	public void setCasename(String casename) {
		this.casename = casename;
	}
	public String getUnlawfulact() {
		return unlawfulact;
	}
	public void setUnlawfulact(String unlawfulact) {
		this.unlawfulact = unlawfulact;
	}
	public String getNlawfulcompanyname() {
		return nlawfulcompanyname;
	}
	public void setNlawfulcompanyname(String nlawfulcompanyname) {
		this.nlawfulcompanyname = nlawfulcompanyname;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public String getUnlawfulfact() {
		return unlawfulfact;
	}
	public void setUnlawfulfact(String unlawfulfact) {
		this.unlawfulfact = unlawfulfact;
	}
	public String getPenaltytype() {
		return penaltytype;
	}
	public void setPenaltytype(String penaltytype) {
		this.penaltytype = penaltytype;
	}
	public String getAdminpenaltytypeandtime() {
		return adminpenaltytypeandtime;
	}
	public void setAdminpenaltytypeandtime(String adminpenaltytypeandtime) {
		this.adminpenaltytypeandtime = adminpenaltytypeandtime;
	}
	public String getPenaltyoffice() {
		return penaltyoffice;
	}
	public void setPenaltyoffice(String penaltyoffice) {
		this.penaltyoffice = penaltyoffice;
	}
	public String getPunishmentdate() {
		return punishmentdate;
	}
	public void setPunishmentdate(String punishmentdate) {
		this.punishmentdate = punishmentdate;
	}
}
