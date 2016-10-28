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
	
    /**
     * @param openid
     * 根据complaintpid查询足迹信息
     */
	public List<Footprint> getFootprintByComplaintpid(String complaintpid);
	/**
	 * @param openid
	 * 根据complaintpid查询足迹信息
	 */
	public List<Footprint> getDisFootprintByOpenid(String openid);
	/**
	 * 执行插入操作
	 * @param footPrint
	 * @return
	 */
	public int doInserFootPrint(Footprint footPrint);
	
	/**
	 * 根据pid修改complainpid和flag
	 * @param footPrint
	 * @return
	 */
	public int updatePrintByComplaintpidandFlag(Footprint footPrint);
	
	
}
