package com.sz.wechat.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.Complaint;
/**
 * 投诉反馈数据服务接口
 * @author sway
 */
@Repository
public interface ComplaintMapper {

	
	/**
	 * 查询企业投诉反馈评分（一年有效期）
	 * @param companyId 企业Code
	 * @return
	 */
	public List<Complaint> getComplaintScoreByCompanyId(String companyId);
	/**
	 * 查询企业投诉反馈信息（一年有效期）
	 * @param companyId 企业Code
	 * @return
	 */
	public List<Complaint> getComplaintByCompanyId(String companyId);
	
	/**
	 * 插入投诉信息
	 * @param complaint 
	 * @return
	 */
	public int insertComplaint(Complaint complaint);

}
