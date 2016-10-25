package com.sz.wechat.entity;

import java.io.Serializable;

/**
 * 餐饮许可证实体类
 * @author sway
 *
 */
public class RepastPublicInfo implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 许可证号
	 */
	private String repastlicence;
	/**
	 * 单位名称
	 */
	private String repastname;
	/**
	 * 法人代表
	 */
	private String legalperson;
	/**
	 * 等级
	 */
	private String grade;
	/**
	 * 经营场所
	 */
	private String address;
	/**
	 * 负责人
	 */
	private String principal;
	/**
	 * 主体类型
	 */
	private String subjecttype;
	/**
	 * 许可范围
	 */
	private String permissionscope;
	/**
	 * 有效期
	 */
	private String validity;
	/**
	 * 发证机关
	 */
	private String certificateoffice;
	/**
	 * 发证日期
	 */
	private String certificatetime;
	public String getRepastlicence() {
		return repastlicence;
	}
	public void setRepastlicence(String repastlicence) {
		this.repastlicence = repastlicence;
	}
	public String getRepastname() {
		return repastname;
	}
	public void setRepastname(String repastname) {
		this.repastname = repastname;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getSubjecttype() {
		return subjecttype;
	}
	public void setSubjecttype(String subjecttype) {
		this.subjecttype = subjecttype;
	}
	public String getPermissionscope() {
		return permissionscope;
	}
	public void setPermissionscope(String permissionscope) {
		this.permissionscope = permissionscope;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getCertificateoffice() {
		return certificateoffice;
	}
	public void setCertificateoffice(String certificateoffice) {
		this.certificateoffice = certificateoffice;
	}
	public String getCertificatetime() {
		return certificatetime;
	}
	public void setCertificatetime(String certificatetime) {
		this.certificatetime = certificatetime;
	}
}
