package com.sz.wechat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.CodeDictMapper;
import com.sz.wechat.entity.CodeDict;
/**
 * 数据字典数据逻辑层
 * @author sway
 *
 */
@Service
public class CodeDictService {
	
	
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

}
