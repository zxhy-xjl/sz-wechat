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
	
    /**
     * @param openid
     * 根据complaintpid查询足迹信息
     */
	public List<Footprint> getFootprintByComplaintpid(String complaintpid)
	{
		
		return this.footprintMapper.getFootprintByOpenid(complaintpid);
		
	}
	
	/**
	 * @param openid
	 * 根据complaintpid查询足迹信息
	 */
	public List<Footprint> getDisFootprintByOpenid(String openid)
	{
		
		return this.footprintMapper.getDisFootprintByOpenid(openid);
	}

	/**
	 * 执行插入操作
	 * @param footPrint
	 * @author guan
	 * @return
	 */
	public int doInserFootPrint(Footprint footPrint){
		return this.footprintMapper.doInserFootPrint(footPrint);
				
	}
	
	/**
	 * 根据pid修改complainpid和flag
	 * @param footPrint
	 * @return
	 */
	public int updatePrintByComplaintpidandFlag(Footprint footPrint)
	{
		return this.footprintMapper.updatePrintByComplaintpidandFlag(footPrint);
		
	}
	public int getCountByCompany(String companyCode){
		return this.footprintMapper.getCountByCompany(companyCode);
	}
	
}
