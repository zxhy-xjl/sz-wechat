package com.sz.wechat.entity;
/**
 * 订单
 * @author leasonlive
 *
 */
public class Order {
	//下单人id
	private String openId;
	//公司代码
	private String companyCode;
	//订单日期
	private String orderDate;
	//订单编号
	private String orderNo;
	//订单总金额
	private String orderTotalMoney;
	//订单状态，下单、支付两个状态，
	private String orderStatus;
	
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderTotalMoney() {
		return orderTotalMoney;
	}
	public void setOrderTotalMoney(String orderTotalMoney) {
		this.orderTotalMoney = orderTotalMoney;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}
