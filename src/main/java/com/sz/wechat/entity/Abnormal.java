package com.sz.wechat.entity;

import java.io.Serializable;
/**
 * 系统自动发现企业信息异常对象，用户系统发现企业没有营业执照、餐饮服务许可证、地址不一致等问题
 * @author leasonlive
 *
 */
public class Abnormal implements Serializable  {
	//主键
	private String pid;
	//企业代码，从二维码或者条码中获取的
	private String companyCode;
	//企业名称，从二维码或者条码中获取的
	private String companyName;
	//发现人的微信表示
	private String openId;
	//没有营业执照，没有为1，其他为有
	private String noRecode;
	//没有餐饮许可证，没有为1，其他为有
	private String noLicence;
	//地址是否发生了变化，发生变化是1，其他为没有发生变化
	private String errorAddress;
	//如果地址发生变化，则新的地址是什么
	private String nowAddress;
	//如果地址发生变化，营业执照上的地址是什么
	private String recodeAddress;
	//异常发现时间
	private String abnormalDate;
	//关闭异常时间
	private String closeDate;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNoRecode() {
		return noRecode;
	}
	public void setNoRecode(String noRecode) {
		this.noRecode = noRecode;
	}
	public String getNoLicence() {
		return noLicence;
	}
	public void setNoLicence(String noLicence) {
		this.noLicence = noLicence;
	}
	public String getErrorAddress() {
		return errorAddress;
	}
	public void setErrorAddress(String errorAddress) {
		this.errorAddress = errorAddress;
	}
	public String getNowAddress() {
		return nowAddress;
	}
	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	public String getRecodeAddress() {
		return recodeAddress;
	}
	public void setRecodeAddress(String recodeAddress) {
		this.recodeAddress = recodeAddress;
	}
	public String getAbnormalDate() {
		return abnormalDate;
	}
	public void setAbnormalDate(String abnormalDate) {
		this.abnormalDate = abnormalDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	
}
