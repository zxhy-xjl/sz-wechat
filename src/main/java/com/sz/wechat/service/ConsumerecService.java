package com.sz.wechat.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.ConsumerecMapper;
import com.sz.wechat.entity.Consumerec;
/**
 * 订单记录数据逻辑层
 * @author sway
 */
@Service
public class ConsumerecService {
	
	/**
	 * 订单记录数据服务接口
	 */
	@Autowired
	private ConsumerecMapper consumerecMapper;

	/**
	 * 批量插入订单数据
	 * @param list
	 * @return
	 */
	public int batchInsertConsumerec(List<Consumerec> list){
		 return this.consumerecMapper.batchInsertConsumerec(list);
	}
	
	/**
	 * 通过订单号获取订单信息
	 * @param oddNumber
	 * @return
	 */
	public List<Consumerec> selectConsumerecByOddNumber(String oddNumber){
		return this.consumerecMapper.selectConsumerecByOddNumber(oddNumber);
	}
	
	/**
	 * 通过openId和注册码确认是否存在记住发票开头的账号
	 * @param openid
	 * @param companyCode
	 * @return
	 */
	public List<Consumerec> selectConsumerByDefaultadd(String openid,String companyCode){
		return this.consumerecMapper.selectConsumerByDefaultadd(openid, companyCode);
	}
	
	/**
	 * 修改支付状态
	 * @param consumerec
	 * @return
	 */
	public int updatePayByOddNumber(Consumerec consumerec){
		return this.consumerecMapper.updatePayByOddNumber(consumerec);
	}
	
	
	/**
	 * 通过支付时间号获取订单信息
	 * @param paytime
	 * @return
	 */
	public List<Consumerec> selectConsumerecByPaytime( String payTime, String companyCode)
	{
		
		return this.consumerecMapper.selectConsumerecByPaytime(payTime,companyCode);
		
	}
	/**
	 * 通过openid和companycode查询订单
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectConsumerecByOpenidandCompanycode(@Param(value="openid")String openid,@Param(value="companycode") String companycode)
	{
		return this.consumerecMapper.selectConsumerecByOpenidandCompanycode(openid, companycode);
		
	}
	
	/**
	 * 通过openid查询订单
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectConsumerecByOpenid(@Param(value="openid")String openid)
	{
		return this.consumerecMapper.selectConsumerecByOpenid(openid);
		
	}
	/**
	 * 通过openid和companycode分组查询订单
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectOddnumberByOpenidandCompanycode(@Param(value="openid")String openid,@Param(value="companycode") String companycode){
		
		return this.consumerecMapper.selectOddnumberByOpenidandCompanycode(openid, companycode);
		
	}
	/**
	 * 得到某人在某个餐厅的下单数量
	 * @param companyId
	 * @param openId
	 * @return
	 */
	public int getCountByCompanyidAndOpenid(String companyId, String openId){
		return this.consumerecMapper.getCountByCompanyidAndOpenid(openId,companyId);
	}
}
