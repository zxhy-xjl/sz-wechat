package com.sz.wechat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.CompanyInfoMapper;
import com.sz.wechat.dao.ComplaintMapper;
import com.sz.wechat.dao.SupervisePunishMapper;
import com.sz.wechat.entity.CompanyInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.SupervisePunish;
/**
 * 餐饮企业数据逻辑层
 * @author sway
 */
@Service
public class CompanyInfoService {

	
	/**
	 * 餐饮企业实现接口
	 */
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	/**
	 * 监管评分实现接口
	 */
	@Autowired
	private SupervisePunishMapper supervisePunishMapper;
	/**
	 * 投诉反馈实现接口
	 */
	@Autowired
	private ComplaintMapper complaintMapper;
	

	/**
	 * 根据餐饮企业注册码获取企业信息
	 * @param companyCode 注册码
	 * @return 餐饮企业实体类
	 */
	public CompanyInfo getCompanyByCode(String companyCode){
		return this.companyInfoMapper.getCompanyByCode(companyCode);
	}
	/**
	 * 查询所有的餐饮企业信息
	 * @return
	 */
	public List<CompanyInfo> getCompanyInfo(){
		return this.companyInfoMapper.getCompanyInfo();
	}
	
	/**
	 * 根据当事人名称查询监管信息
	 * @param supervisePunish
	 * @return
	 */
	public List<SupervisePunish> getSuperviseLikeCompanyName(SupervisePunish supervisePunish){
		return this.supervisePunishMapper.getSuperviseLikeCompanyName(supervisePunish);
	}
	
	/**
	 * 查询企业反馈评分（一年有效期）
	 * @param companyId 企业ID
	 * @return
	 */
	public List<Complaint>  getComplaintScoreByCompanyId(String companyId){
		return this.complaintMapper.getComplaintScoreByCompanyId(companyId);
	}
	/**
	 * 查询企业投诉反馈信息（一年有效期）
	 * @param companyId 企业ID
	 * @return
	 */
	public List<Complaint> getComplaintByCompanyId(String companyId){
		return this.complaintMapper.getComplaintByCompanyId(companyId);
	}
}
