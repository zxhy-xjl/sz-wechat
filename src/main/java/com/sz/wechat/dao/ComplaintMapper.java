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
	 * 查询企业投诉反馈信息
	 * @param companyId 企业Code
	 * @param complaintType 投诉类型
	 * @return
	 */
	public List<Complaint> getComplaintBycomplainTypeAndcompanyId(String companyId,String complaintType);
}
