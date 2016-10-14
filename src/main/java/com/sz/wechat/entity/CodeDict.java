package com.sz.wechat.entity;
/**
 * 字典数据结构
 * @author sway
 *
 */
public class CodeDict {

	/**
	 * 编码Id
	 */
	private String code_id;
	/**
	 * 编码类型
	 */
	private String 	codetype;
	/**
	 * 码表说明
	 */
	private String 	codedescript;
	/**
	 * 类型说明
	 */
	private String 	descript;
	public String getCode_id() {
		return code_id;
	}
	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}
	public String getCodetype() {
		return codetype;
	}
	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}
	public String getCodedescript() {
		return codedescript;
	}
	public void setCodedescript(String codedescript) {
		this.codedescript = codedescript;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
}
