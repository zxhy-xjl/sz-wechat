package com.sz.wechat.dao;
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
}
