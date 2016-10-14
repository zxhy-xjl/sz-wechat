package com.sz.wechat.entity;

import java.io.Serializable;
import java.sql.Blob;

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
	private Blob menuphoto;
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
	public Blob getMenuphoto() {
		return menuphoto;
	}
	public void setMenuphoto(Blob menuphoto) {
		this.menuphoto = menuphoto;
	}
}
