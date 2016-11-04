package com.sz.wechat.basic;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.sz.wechat.entity.CodeDict;
import com.sz.wechat.service.CodeDictService;
import com.sz.wechat.utils.StringUtils;

/**
 * 字典基本信息服务操作类
 * @author sway
 */
@Component
public class DictCommon implements  ServletContextAware{
	
	public static List<Map<String,LinkedHashMap<String,CodeDict>>> dictListMap = null;
	
	/**
	 * 字典数据逻辑层
	 */
	@Autowired
	private CodeDictService codeDictService;
	
	/**
	 *	根据字典类型和字典代码获取字典值
	 *	@param type字典类型
	 *	@param code字典代码
	 *	@return String
	 */
	public static String getDictValue(String type,String code){
		List<Map<String,LinkedHashMap<String,CodeDict>>> listMap= DictCommon.getDictList(type);
		CodeDict codeDict = null;
		StringBuffer _dictVal=new StringBuffer("");
		 for (Map<String, LinkedHashMap<String, CodeDict>> map : listMap) {
			 if(null!=map.get(type) && null!=map.get(type).get(code)){
					codeDict=map.get(type).get(code);
					_dictVal.append(codeDict.getCodedescript());
				}else{
					_dictVal.append("");
				}
		 }
		return _dictVal.toString();
	}
	
	/**
	 * 根据字典类型获取字典集合
	 * @param DictType
	 * @return
	 */
	public static List<CodeDict> getDictListEn(String DictType){
		List<CodeDict> listDict = new ArrayList<CodeDict>();
		if(!"".equals(DictType)){
			for (Map<String, LinkedHashMap<String, CodeDict>> map : dictListMap) {
				if(null != map.get(DictType)){
					for (Entry<String,CodeDict> entry : map.get(DictType).entrySet()) {
						listDict.add(map.get(DictType).get(entry.getKey()));
					}
				}
			} 
		} 
		return listDict;
	}
	
	/**
	 *	根据字典类型获取字典列表
	 *	@param type字典类型
	 *	@param code字典代码
	 *	@return String
	 */
	public static List<Map<String,LinkedHashMap<String,CodeDict>>> getDictList(String type){
		List<Map<String,LinkedHashMap<String,CodeDict>>> listMap = new ArrayList<Map<String,LinkedHashMap<String,CodeDict>>>();
		if(null != dictListMap && dictListMap.size()>0){
			for (Map<String, LinkedHashMap<String, CodeDict>> map : dictListMap) {
				if(null != map.get(type)){
					listMap.add(map);
				}
			}
		}
		return  listMap;
	}
	/**
	 *	清除内在对象 
	 */
	public static void clearDictMap(){
		dictListMap.clear();
		dictListMap=null;
	}


	/**
	 * 初始化
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		if(null == dictListMap){
			dictListMap=codeDictService.loadAllDicInfo();
		}
	}
}
