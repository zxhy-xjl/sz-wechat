/**
 * 
 */
package com.sz.wechat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.Footprint;

/**
 * 足迹接口
 * @author linhd
 *
 */
@Repository
public interface FootprintMapper {
    /**
     * @param openid
     * 根据openid查询足迹信息
     */
	public List<Footprint> getFootprintByOpenid(String openid);
	
	
	
}
