package com.sz.wechat.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.CodeDictMapper;
import com.sz.wechat.entity.CodeDict;
/**
 * 数据字典数据逻辑层
 * @author sway
 *
 */
@Component("codeDictService")  
public class CodeDictService implements CodeDictMapper  {
	/*创建日志输出文件*/
	private static Logger log=Logger.getLogger(CodeDictService.class);
	
	/**
	 * 数据字典服务接口
	 */
	@Autowired
	private CodeDictMapper codeDictMapper;
	
	
	/**
	 * 通过字典类型获取字典信息
	 * @param type 字典类型
	 * @return
	 */
	public List<CodeDict> getDictByType(String type){
		return this.codeDictMapper.getDictByType(type);
	}
	
	
	/**
	 * 初始加载所有字典信息
	 * @return
	 */
	public List<Map<String,LinkedHashMap<String,CodeDict>>> loadAllDicInfo(){
		List<CodeDict> dictList = this.codeDictMapper.getAllDict();
		String type_bef="";
		String type_after="";
		boolean isSave=false;
		List<Map<String,LinkedHashMap<String,CodeDict>>> dicListMap = new ArrayList<Map<String,LinkedHashMap<String,CodeDict>>>();
		Map<String,LinkedHashMap<String,CodeDict>> dicMap = null;
		if(null != dictList && dictList.size()>0){
			LinkedHashMap<String,CodeDict> linkMap=null;
			for (CodeDict _codeDict : dictList) {
				type_bef = _codeDict.getCodetype();
				if(!type_bef.equals(type_after)){
					isSave = true;
				}
				if(isSave){
					linkMap=new LinkedHashMap<String,CodeDict>();
				}
				linkMap.put(_codeDict.getCode_id().trim(), _codeDict);
				if(isSave){
					dicMap = new HashMap<String,LinkedHashMap<String,CodeDict>>();
					dicMap.put(_codeDict.getCodetype().trim(),linkMap);
					log.debug("成功加载 ["+_codeDict.getDescript()+"] 类型的字典");
				}
				type_after=type_bef;
				isSave=false;
				dicListMap.add(dicMap);
			}
		}
		return dicListMap;
	}


	@Override
	public List<CodeDict> getAllDict() {
		// TODO Auto-generated method stub
		System.out.println(this.codeDictMapper);
		return null;
	}

}
