package com.sz.wechat.entity;
import java.io.Serializable;

/**
 * 健康证数据结构
 * @author sway
 *
 */
public class PersonHealth implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键编号
	 */
	private String healthid;
	/**
	 * 企业编号
	 */
	private String companycode;
	/**
	 * 健康证身份证
	 */
	private String healthpersoncode;
	
	/**
	 * 姓名
	 */
	private String name;
	public String getHealthid() {
		return healthid;
	}
	public void setHealthid(String healthid) {
		this.healthid = healthid;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getHealthpersoncode() {
		return healthpersoncode;
	}
	public void setHealthpersoncode(String healthpersoncode) {
		this.healthpersoncode = healthpersoncode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
