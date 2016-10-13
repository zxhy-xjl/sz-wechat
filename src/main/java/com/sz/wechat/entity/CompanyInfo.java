package com.sz.wechat.entity;
/**
 * 企业信息数据结构
 * @author sway
 *
 */
public class CompanyInfo {

	/**
	 * 企业注册码
	 */
	private String companycode;
	/**
	 * 企业名称
	 */
	private String companyname;
	/**
	 * 企业类型
	 */
	private String companytype;
	/**
	 * 法人名称
	 */
	private String legalperson;
	/**
	 * 注册资本
	 */
	private String registcapital;
	/**
	 * 成立时间
	 */
	private String createtime;
	/**
	 * 公司地址
	 */
	private String companyaddress;
	/**
	 * 经营时限开始
	 */
	private String operatestart;
	/**
	 * 经营时限结束
	 */
	private String operateend;
	/**
	 * 经营范围
	 */
	private String operatescope;
	/**
	 * 注册机关
	 */
	private String registeroffice;
	/**
	 *核准日期
	 */
	private String filetime;
	/**
	 * 企业状态
	 */
	private String companystatus;
	/**
	 * 商家介绍
	 */
	private String companyintro;
	/**
	 * 积分
	 */
	private int score;
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanytype() {
		return companytype;
	}
	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public String getRegistcapital() {
		return registcapital;
	}
	public void setRegistcapital(String registcapital) {
		this.registcapital = registcapital;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCompanyaddress() {
		return companyaddress;
	}
	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}
	public String getOperatestart() {
		return operatestart;
	}
	public void setOperatestart(String operatestart) {
		this.operatestart = operatestart;
	}
	public String getOperateend() {
		return operateend;
	}
	public void setOperateend(String operateend) {
		this.operateend = operateend;
	}
	public String getOperatescope() {
		return operatescope;
	}
	public void setOperatescope(String operatescope) {
		this.operatescope = operatescope;
	}
	public String getRegisteroffice() {
		return registeroffice;
	}
	public void setRegisteroffice(String registeroffice) {
		this.registeroffice = registeroffice;
	}
	public String getFiletime() {
		return filetime;
	}
	public void setFiletime(String filetime) {
		this.filetime = filetime;
	}
	public String getCompanystatus() {
		return companystatus;
	}
	public void setCompanystatus(String companystatus) {
		this.companystatus = companystatus;
	}
	public String getCompanyintro() {
		return companyintro;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setCompanyintro(String companyintro) {
		this.companyintro = companyintro;
	}
}
