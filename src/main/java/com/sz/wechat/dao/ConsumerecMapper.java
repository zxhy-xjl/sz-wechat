package com.sz.wechat.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.sz.wechat.entity.Consumerec;

/**
 * 订单服务接口
 * @author sway
 *
 */
@Repository
public interface ConsumerecMapper {
	
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	public int batchInsertConsumerec(List<Consumerec> list);
	
	/**
	 * 通过单号查询订单
	 * @param oddNumber 订单号
	 * @return
	 */
	public List<Consumerec> selectConsumerecByOddNumber(String oddNumber);
	
	/**
	 * 修改支付状态
	 * @param consumerec
	 * @return
	 */
	public int updatePayByOddNumber(Consumerec consumerec);

	/**
	 * 通过下单时间号查询订单
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectConsumerecByPaytime(@Param(value="payTime")String paytime,@Param(value="companyCode") String companycode);
	/**
	 * 通过openid和companycode查询订单
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectConsumerecByOpenidandCompanycode(@Param(value="openid")String openid,@Param(value="companycode") String companycode);
	
}
