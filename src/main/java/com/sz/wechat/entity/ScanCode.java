package com.sz.wechat.entity;

import java.io.Serializable;

/**
 * 扫一扫数据结构
 * @author sway
 *
 */
public class ScanCode implements Serializable  {

	private static final long serialVersionUID = 1L;
	/**
	 * 入场券
	 */
	private String ticket;
	/**
	 * 随机字符串
	 */
	private String nonce;
	/**
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * 签名
	 */
	private String signature;
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
