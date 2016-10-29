package com.sz.wechat.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	public List<Complaint> getComplaintScoreByCompanyId(@Param(value="companyid") String companyId, @Param("status") String status);

	
	/**
	 * 查询企业投诉反馈页面(一年有效期)
	 * @param companyId
	 * @param openId
	 * @return
	 */
	public List<Complaint> getComplaintScoreByCompanyIdAndOpenid(String companyId,String openId);
	/**
	 * 查询企业投诉反馈信息（一年有效期）
	 * @param companyId 企业Code
	 * @param status 状态，如果为空，代表查询所有的
	 * @return
	 */
	public List<Complaint> getComplaintByCompanyId(@Param(value="companyid") String companyId, @Param("status") String status);
	/**
	 * 查询企业投诉信息（政府端）
	 *
	 * @return
	 */
	public List<Complaint> getComplaintInfo();
	/**
	 * 连表查询投诉信息
	 * @return
	 */
	public List<Complaint> getMoreComplaintInfo();
	
	/**
	 * 通过openid查询投诉信息
	 * @param Openid
	 * @return
	 */
	public List<Complaint> getComplaintByOpenid(String Openid);
	/**
	 * 通过pid查询投诉信息
	 * @param pid
	 * @return
	 */
	public Complaint getComplaintInfoByPid(String pid);
	/**
	 * 通过pid修改投诉处理状态
	 * @param pid
	 */
    public int updateStatusByPid(Complaint complaint);
    /**
     * 通过pid修改投诉处理状态和反馈信息
     * @param pid
     */
    public int updateStatusandFeedByPid(Complaint complaint);
	
	/**
	 * 插入投诉信息
	 * @param complaint 
	 * @return
	 */
	public int insertComplaint(Complaint complaint);

	/**
	 * 得到某人在某个餐厅的投诉数量
	 * @param companyId
	 * @param openid
	 * @return
	 */
	public int getComplaintCountByCompanyIdAndOpenid(@Param(value="companyid") String companyid, @Param("openid") String openid);
	/**
	 * 得到餐厅所有的投诉,投诉状态为4的，也就是已经完成的状态
	 * @param companyid
	 * @return
	 */
	public int getComplaintCountByCompanyId(@Param(value="companyid") String companyid, @Param(value="status") String status);
}
