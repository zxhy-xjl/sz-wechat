package com.sz.wechat.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.SupervisePunishMapper;
/**
 *  监管得分数据逻辑层
 * @author sway
 *
 */
import com.sz.wechat.entity.SupervisePunish;
@Service
public class SupervisePunishService {

	/**
	 * 监管处罚服务接口
	 */
	@Autowired
	private SupervisePunishMapper supervisePunishMapper;
	
	/**
	 * 根据企业名称查询监管处罚信息
	 * @param companyName
	 * @return
	 */
	public SupervisePunish getSuperviseByCompanyName(SupervisePunish supervisePunish){
		return this.supervisePunishMapper.getSuperviseByCompanyName(supervisePunish);
	}
}
