package com.sz.wechat.entity;

import java.io.Serializable;

/**
 * 评分实体类
 * @author sway
 *
 */
public class Grade implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 扣分
	 */
	private String score;
	/**
	 * 消息
	 */
	private String error;
	/**
	 * 一共积分
	 */
	private String allscore;
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 结果
	 */
	private String result;
	
	/**
	 * 营业执照请求地址
	 */
	private String url;
	
	
	public Grade(){
	}
	
	public Grade(String type,String allscore){
		this.type=type;
		this.allscore=allscore; 
	}
	
	public Grade(String type,String score,String name,String result,String error,String url){
		this.type=type;
		this.score=score;
		this.name= name;
		this.result=result;
		this.error=error;
		this.url = url;
	}
	public Grade(String type,String score,String name,String result,String error){
		this.type=type;
		this.score=score;
		this.name= name;
		this.result=result;
		this.error=error;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getAllscore() {
		return allscore;
	}
	public void setAllscore(String allscore) {
		this.allscore = allscore;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
