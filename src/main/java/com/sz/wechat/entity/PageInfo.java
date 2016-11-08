/**
 * 
 */
package com.sz.wechat.entity;

/**
 * 日志页面记录
 * @author lenovo
 *
 */
public class PageInfo {
    
	//action名称
	private String actionname;
	//访问页面名称
	private String visitpage;
	//说明
	private String description;

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public String getVisitpage() {
		return visitpage;
	}

	public void setVisitpage(String visitpage) {
		this.visitpage = visitpage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
