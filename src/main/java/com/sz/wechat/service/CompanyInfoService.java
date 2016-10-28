package com.sz.wechat.service;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.CompanyInfoMapper;
import com.sz.wechat.dao.ComplaintMapper;
import com.sz.wechat.dao.SupervisePunishMapper;
import com.sz.wechat.entity.CompanyInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Evaluate;
import com.sz.wechat.entity.Grade;
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
	 * 积分数据逻辑层
	 */
	@Autowired
	private EvaluateService evaluateService;
	
	private final String KEYWORD="警告";
	private final String KEYWORD_0="罚款";
	private final String KEYWORD_1="没收";
	private final String KEYWORD_2="停产停业";
	private final String KEYWORD_3="吊销执照";
	private final String KEYWORD_4="暂扣";
	

	/**
	 * 根据餐饮企业注册码获取企业信息
	 * @param companyCode 注册码
	 * @return 餐饮企业实体类
	 */
	public CompanyInfo getCompanyByCode(String companyCode){
		return this.companyInfoMapper.getCompanyByCode(companyCode);
	}
	
	/**
	 * 根据餐饮企业注册码和企业类型获取企业信息
	 * @param companyCode 注册码
	 * @param type 企业类型
	 * @return
	 */
	public CompanyInfo getCompanyByCodeAndType(String companyCode,String type){
		return this.companyInfoMapper.getCompanyByCodeAndType(companyCode, type);
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
	/**
	 * 得到我在该餐厅的投诉数量
	 * @param compnayId
	 * @param opendid
	 * @return
	 */
	public int getComplaintCountByCompanyIdAndOpenid(String companyId,String openid){
		return this.complaintMapper.getComplaintCountByCompanyIdAndOpenid(companyId,openid);
	}
	
	
	/**
	 * 插入投诉信息
	 * @param complaint
	 * @return
	 */
	public int insertComplaint(Complaint complaint)
	{
		return this.complaintMapper.insertComplaint(complaint);
		
	}
	
	public List<Complaint> getComplaintByOpenid(String Openid)
	{
		return this.complaintMapper.getComplaintByOpenid(Openid);
		
	}
	
	 public Complaint getComplaintInfoByPid(String pid)
	{
		return this.complaintMapper.getComplaintInfoByPid(pid);
	}
	 /**
	  * 得到该餐厅的综合得分
	  * @return
	  */
	 public int getScore(String companyCode){
		 	int score = 100;
			if(!"".equals(companyCode)){
				CompanyInfo companyInfo = getCompanyByCode(companyCode);
				if(null != companyInfo){
					//资质类
					//营业执照
					if("".equals(companyInfo.getCompanyrecode())|| null == companyInfo.getCompanyrecode()){
						score = score - 30;
					}
					//餐饮服务许可证
					if("".equals(companyInfo.getLicence()) || null == companyInfo.getLicence()){
						score = score - 30;
					}
					//处罚类
					SupervisePunish supervisePunish = new SupervisePunish();
					supervisePunish.setNlawfulcompanyname(companyInfo.getCompanyname());
					List<SupervisePunish> list = getSuperviseLikeCompanyName(supervisePunish);
					if(null != list && list.size() > 0){
						//根据关键字判断
						for (SupervisePunish _supervisePunish : list) {
							String illegalType = _supervisePunish.getPenaltytype();
							//判断是否存在吊销执照
							if(illegalType.indexOf(KEYWORD_3)!=-1){
								score = score - 5;
							}else if(illegalType.indexOf(KEYWORD_4)!=-1){
								score = score - 5;
							}
							//停产停业
							if(illegalType.indexOf(KEYWORD_2)!=-1){
								score = score - 4;
							}
							//没收违法所得
							if(illegalType.indexOf(KEYWORD_1)!=-1){
								score = score - 3;
							}
							//罚款
							if(illegalType.indexOf(KEYWORD_0)!=-1){
								score = score - 2;
							}
							//警告
							if(illegalType.indexOf(KEYWORD)!=-1){
								score = score - 1;
							}
						}
					}
					//投诉类
					int allgrade = 5;
					int gradeStat=0;
					List<Complaint> complaintList = getComplaintByCompanyId(companyCode);
					//投诉
					if(null != complaintList && complaintList.size() > 0){
						for (Complaint complaint : complaintList) {
							if(null != complaint){
								if(score > 2){
									score = score - 2;
								}else{
									score = 0;
									break;
								}
							}
						}
					}
					//评分
					List<Evaluate> evaluateList = this.evaluateService.getEvaluateByOpenIdAndCompanyCode(companyCode);
					if(null != evaluateList && evaluateList.size()>0){
						for(Evaluate evaluate:evaluateList){
							if(null != evaluate.getEvaluate()){
								gradeStat = Integer.parseInt(evaluate.getEvaluate())+gradeStat;
							}
						}
						gradeStat = gradeStat/evaluateList.size();
						gradeStat = allgrade  - gradeStat;
						if(score > gradeStat){
							score = score - gradeStat;
						}else{
							score = 0;
						}
						gradeStat=0;
					}
				}
			}
		 return score;
	 }
	 
	 
	 /**
	  * 计算评星分数
	  * @param openId
	  * @param companyCode
	  * @return
	  */
	 public int getEvaluate(String companyCode){
		 int allScore = 0;
		 List<Evaluate> evaluateList = this.evaluateService.getEvaluateByOpenIdAndCompanyCode(companyCode);
		 if(null != evaluateList && evaluateList.size()>0){
				int length = 0;
				for (Evaluate evaluate : evaluateList) {
					if(!"".equals(evaluate.getEvaluate())){
						length = length +1;
						allScore = allScore + Integer.parseInt(evaluate.getEvaluate());
					}
				}
				allScore = allScore / length;
			}
		return allScore;
	 }
	public String getFace(String companyCode){
		CompanyInfo companyInfo = this.getCompanyByCode(companyCode);
		if (companyInfo == null){
			return null;
		}
		String face = this.companyInfoMapper.getFaceByLicence(companyInfo.getLicence());
		return face;
		
	}
		
	
}
