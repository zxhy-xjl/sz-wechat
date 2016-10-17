package com.sz.wechat.entity;
import java.io.Serializable;
/**
 * 订单数据结构
 * @author sway
 *
 */
public class Consumerec  implements Serializable   {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String pid;
	/**
	 * 企业注册码
	 */
	private String companycode;
	/**
	 * 菜单ID
	 */
	private String menuid;
	/**
	 * 购买单品数量
	 */
	private String buynum;
	/**
	 * 购买方式
	 */
	private String paytype;
	/**
	 * 开票单位
	 */
	private String billunit;
	/**
	 * 微信OPENID
	 */
	private String openid;
	/**
	 * 消费时间
	 */
	private String paytime;
	
	/**
	 * 单号
	 */
	private String oddnumber;
	/**
	 * 单价
	 */
	private String price;
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
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getBuynum() {
		return buynum;
	}
	public void setBuynum(String buynum) {
		this.buynum = buynum;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getBillunit() {
		return billunit;
	}
	public void setBillunit(String billunit) {
		this.billunit = billunit;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOddnumber() {
		return oddnumber;
	}
	public void setOddnumber(String oddnumber) {
		this.oddnumber = oddnumber;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
