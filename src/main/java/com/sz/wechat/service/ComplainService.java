package com.sz.wechat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.ComplaintMapper;
import com.sz.wechat.entity.Complaint;
/**
 * 投诉数据服务逻辑层
 * @author sway
 *
 */
@Service
public class ComplainService {

	/**
	 * 投诉反馈实现接口
	 */
	@Autowired
	private ComplaintMapper complaintMapper;
	
	
	/**
	 * 查询企业反馈评分（一年有效期）
	 * @param companyId 企业ID
	 * @return
	 */
	public List<Complaint>  getComplaintScoreByCompanyId(String companyId){
		return this.complaintMapper.getComplaintScoreByCompanyId(companyId);
	}
	/**
	 * 查询企业投诉反馈评分（一年有效）
	 * @param companyCode
	 * @return
	 */
	public List<Complaint> getComplaintScoreByCompanyIdAndOpenid(String companyCode,String openid){
		return this.complaintMapper.getComplaintScoreByCompanyIdAndOpenid(companyCode,openid);
	}
	/**
	 * 得到我在该餐厅的投诉数量
	 * @param companyCode
	 * @param openid
	 * @return
	 */
	public int getComplaintCountByCompanyIdAndOpenid(String companyid,String openid){
		return this.complaintMapper.getComplaintCountByCompanyIdAndOpenid(companyid,openid);
	}
	/**
	 * 政府端执行投诉的查询操作
	 * @param companyCode
	 * @param openid
	 * @return
	 */
	public List<Complaint> getComplaintInfo(){
		return this.complaintMapper.getComplaintInfo();
	}
	/**
	 * 政府端执行投诉的查询操作
	 * @param companyCode
	 * @param openid
	 * @return
	 */
	public List<Complaint> getMoreComplaintInfo(){
		return this.complaintMapper.getMoreComplaintInfo();
	}
	
}
