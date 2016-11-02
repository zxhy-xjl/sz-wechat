package com.sz.wechat.entity;
import java.io.Serializable;

/**
 * 菜单数据结构
 * @author sway
 *
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 菜单ID
	 */
	private String menuid;
	/**
	 * 菜单名称
	 */
	private String menuname;
	/**
	 * 菜单品种
	 */
	private String menutype;
	/**
	 * 菜品特色
	 */
	private String feature;
	/**
	 * 单价
	 */
	private String price;
	/**
	 * 菜品图片
	 */
	private byte[] menuphoto;
	
	/**
	 * 企业Code
	 */
	private String companycode;
	
	/**
	 * 保存路径
	 */
	private String path;
	
	
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
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenutype() {
		return menutype;
	}
	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public byte[] getMenuphoto() {
		return menuphoto;
	}
	public void setMenuphoto(byte[] menuphoto) {
		this.menuphoto = menuphoto;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
