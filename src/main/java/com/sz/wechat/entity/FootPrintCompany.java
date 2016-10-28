package com.sz.wechat.entity;
/**
 * 在足迹页面上显示需要使用的类，以企业名称为主线，一个企业有多个扫桌操作会合并到一起
 * @author leasonlive
 *
 */
public class FootPrintCompany {
	private String companyCode;
	private String companyName;
	private int fontPrintcount;
	private int complaintCount;
	private int evaluate;
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
	public int getFontPrintcount() {
		return fontPrintcount;
	}
	public void setFontPrintcount(int fontPrintcount) {
		this.fontPrintcount = fontPrintcount;
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
