package com.sz.wechat.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.PersonHealth;
/**
 * 企业健康证数据接口
 * @author sway
 *
 */
@Repository
public interface PersonHealthMapper {

	
	/**
	 * 根据公司企业编号获取公司下员工健康证
	 * @param companyCode
	 * @return
	 */
	public List<PersonHealth> getPersonHealthByCompanyCode(String companyCode);
}
