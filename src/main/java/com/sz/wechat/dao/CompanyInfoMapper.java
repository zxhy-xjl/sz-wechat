package com.sz.wechat.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.sz.wechat.entity.CompanyInfo;
/**
 * 餐饮企业数据服务操作接口
 * @author sway
 *
 */
@Repository
public interface CompanyInfoMapper {

	/**
	 * 根据餐饮企业注册码查询公司信息
	 * @param companyCode 公司注册码
	 * @return 企业公司数据实体类 
	 */
	public CompanyInfo  getCompanyByCode(String companyCode);
	
	/**
	 * 查询所有的餐饮企业
	 * @return
	 */
	public List<CompanyInfo> getCompanyInfo();
	
	/**
	 * 根据企业注册码和类型获取企业信息
	 * @param companyCode
	 * @param type
	 * @return
	 */
	public CompanyInfo getCompanyByCodeAndType(String companyCode,String type);
}
