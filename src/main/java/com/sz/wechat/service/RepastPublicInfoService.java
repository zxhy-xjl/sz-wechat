package com.sz.wechat.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.wechat.dao.RepastPublicInfoMapper;
import com.sz.wechat.entity.RepastPublicInfo;

/**
 * 许可证信息数据逻辑层
 * @author sway
 */
@Service
public class RepastPublicInfoService {

	/**
	 * 许可证数据餐饮服务接口
	 */
	@Autowired
	private RepastPublicInfoMapper repastPublicInfoMapper;
	
	
	/**
	 * 根据许可证号获取基本信息
	 * @param repastlicence
	 * @return
	 */
	public RepastPublicInfo getPublicInfo(String repastlicence){
		return this.repastPublicInfoMapper.getPublicInfo(repastlicence);
	}
}
