/**
 * 
 */
package com.sz.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.wechat.dao.PageInfoMapper;
import com.sz.wechat.entity.PageInfo;

/**
 * 日志页面名称业务层
 * @author linhd
 *
 */
@Service
public class PageInfoService {

	@Autowired
	private PageInfoMapper pageInfoMapper;
	/**
	 * 根据actionname获取页面名称
	 * @param openid
	 * @return
	 */
	public PageInfo getPagenameByAction(String actionname)
	{
		return this.pageInfoMapper.getPagenameByAction(actionname);
	}
	
}
