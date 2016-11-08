/**
 * 
 */
package com.sz.wechat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.PageInfo;

/**
 * 日志页面名称接口
 * @author linhd
 *
 */
@Repository
public interface PageInfoMapper {

	
	
	
	/**
	 * 根据actionname获取页面名称
	 * @param openid
	 * @return
	 */
	public PageInfo getPagenameByAction(String actionname);
}
