package com.sz.wechat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.EvaluateMapper;
import com.sz.wechat.entity.Evaluate;
/**
 * 评分数据逻辑层
 * @author sway
 *
 */
@Service
public class EvaluateService {
	
	/**
	 * 评分服务接口
	 */
	@Autowired
	private EvaluateMapper evaluateMapper;
	
	/**
	 * 执行插入操作
	 * @param evaluate
	 * @return
	 */
	public int doInsertEvaluate(Evaluate evaluate){
		return this.evaluateMapper.doInsertEvalate(evaluate);
	}
	
	/**
	 * 通过openid和企业code获取积分信息
	 * @param openId
	 * @param companyCode
	 * @return
	 */
	public List<Evaluate> getEvaluateByOpenIdAndCompanyCode(String openId,String companyCode){
		return this.evaluateMapper.getEvaluateByOpenIdAndCompanyCode(openId, companyCode);
	}
}
