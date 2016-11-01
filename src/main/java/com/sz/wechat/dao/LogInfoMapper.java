/**
 * 
 */
package com.sz.wechat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.Footprint;
import com.sz.wechat.entity.LogInfo;

/**
 * 记录访问日志接口
 * @author lenovo
 *
 */
@Repository
public interface LogInfoMapper {

	/**
	 * 插入日志
	 * @param loginfo
	 * @return
	 */
public int insertLog(LogInfo loginfo);
	
	/**
	 * 根据openid获取日志
	 * @param openid
	 * @return
	 */
	public List<LogInfo> getLogByOpenid(String openid);
}
