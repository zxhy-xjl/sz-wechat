package com.sz.wechat.entity;
/**
 * 在足迹页面上显示需要使用的类，以企业名称为主线，一个企业有多个扫桌操作会合并到一起
 * @author leasonlive
 *
 */
public class FootPrintCompany {
	//餐厅代码
	private String companyCode;
	//餐厅名称
	private String companyName;
	//订单数量
	private int consumerecCount;
	//投诉数量
	private int complaintCount;
	//评星
	private int evaluate;
	//综合得分
	private int score;
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
	
	public int getConsumerecCount() {
		return consumerecCount;
	}
	public void setConsumerecCount(int consumerecCount) {
		this.consumerecCount = consumerecCount;
	}
	public int getComplaintCount() {
		return complaintCount;
	}
	public void setComplaintCount(int complaintCount) {
		this.complaintCount = complaintCount;
	}
	public int getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(int evaluate) {
		this.evaluate = evaluate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
