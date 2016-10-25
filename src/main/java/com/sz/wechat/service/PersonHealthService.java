package com.sz.wechat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.wechat.dao.PersonHealthMapper;
import com.sz.wechat.entity.PersonHealth;
/**
 * 健康证数据逻辑层
 * @author sway
 */
@Service
public class PersonHealthService {

	/**
	 * 健康证数据服务接口
	 */
	@Autowired
	private PersonHealthMapper personHealthMapper;
	
	
	
	/**
	 * 根据公司编号获取公司下健康证
	 * @param companyCode
	 * @return
	 */
	public List<PersonHealth> getPersonHealthByCompanyCode(String companyCode){
		return this.personHealthMapper.getPersonHealthByCompanyCode(companyCode);
	}
}
