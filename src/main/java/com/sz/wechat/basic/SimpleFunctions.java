package com.sz.wechat.basic;

/**
 * 自定义函数
 * @author sway
 *
 */
public class SimpleFunctions {

	
	/**
	 *	字典翻译
	 */
	public static String parseDict(String type,String code){
		return DictCommon.getDictValue(type,code);
	}
}
