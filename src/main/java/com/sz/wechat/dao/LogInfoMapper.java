/**
 * 
 */
package com.sz.wechat.dao;

import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.LogInfo;

/**
 * 记录访问日志接口
 * @author lenovo
 *
 */
@Repository
public interface LogInfoMapper {

	
public int insertLog(LogInfo loginfo);
	
	
	
}
