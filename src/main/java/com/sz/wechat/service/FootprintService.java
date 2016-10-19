/**
 * 
 */
package com.sz.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.wechat.dao.FootprintMapper;
import com.sz.wechat.entity.Footprint;

/**
 * @author linhd
 *
 */
@Service
public class FootprintService {

	@Autowired
	private FootprintMapper footprintMapper;
	
	public List<Footprint> getFootprintByOpenid(String openid)
	{
		return this.footprintMapper.getFootprintByOpenid(openid);
		
	}
	
}
