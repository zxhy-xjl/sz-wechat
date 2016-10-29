/**
 * 
 */
package com.sz.wechat.entity;

/**
 * 餐厅控制台辅助类
 * @author linhd
 *
 */
public class RestaurantConsoleHelper {
	//订单号
	private String ordernum;
	//桌号
	private String tablenum;
	//订单总价
    private String totalprice;
    //菜品总数
    private String coursenum;
    //订单状态
    private String orderstatus;
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getTablenum() {
		return tablenum;
	}
	public void setTablenum(String tablenum) {
		this.tablenum = tablenum;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getCoursenum() {
		return coursenum;
	}
	public void setCoursenum(String coursenum) {
		this.coursenum = coursenum;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
    
}
