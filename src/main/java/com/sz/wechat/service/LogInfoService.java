/**
 * 
 */
package com.sz.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.wechat.dao.LogInfoMapper;
import com.sz.wechat.entity.LogInfo;

/**
 * 访问记录业务层
 * @author linhd
 *
 */
@Service
public class LogInfoService {

	@Autowired
	private LogInfoMapper logInfoMapper;
	
	public int insertLog(LogInfo loginfo)
	{
		return this.logInfoMapper.insertLog(loginfo);
	}
	
	/**
	 * 根据openid获取日志
	 * @param openid
	 * @return
	 */
	public List<LogInfo> getLogByOpenid(String openid)
	{
		return this.logInfoMapper.getLogByOpenid(openid);
	}
}
