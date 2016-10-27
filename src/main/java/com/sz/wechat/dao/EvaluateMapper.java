package com.sz.wechat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.Evaluate;

/**
 * 积分服务接口
 * @author sway
 *
 */
@Repository
public interface EvaluateMapper {

	
	/**
	 * 执行分数插入
	 * @param evalate
	 * @return
	 */
	public int doInsertEvalate(Evaluate evalate);
	
	/**
	 * 通过openID和companyCode查询积分信息
	 * @param openId
	 * @param companyCode
	 * @return
	 */
	public List<Evaluate> getEvaluateByOpenIdAndCompanyCode(String openId,String companyCode);
}
