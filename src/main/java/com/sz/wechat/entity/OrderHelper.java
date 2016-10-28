/**
 * 
 */
package com.sz.wechat.entity;

/**
 * 查询餐厅详情记录辅助类
 * @author lenovo
 *
 */
public class OrderHelper {
    //订单编号
	private String oddnumber;
	//付款时间
	private String paytime;
	//单次消费总价
	private float price;
	//投诉类型
	private String complainttype;
    //投诉pid
	private String complaintpid;
	
	
	public String getComplaintpid() {
		return complaintpid;
	}

	public void setComplaintpid(String complaintpid) {
		this.complaintpid = complaintpid;
	}

	public String getOddnumber() {
		return oddnumber;
	}

	public void setOddnumber(String oddnumber) {
		this.oddnumber = oddnumber;
	}

	public String getPaytime() {
		return paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getComplainttype() {
		return complainttype;
	}

	public void setComplainttype(String complainttype) {
		this.complainttype = complainttype;
	}
	
	
	
}
