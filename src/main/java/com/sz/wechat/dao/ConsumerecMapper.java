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
	
	/**
	 * 通过companycode模糊查询订单表（餐厅客户端专用）
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectConsumerecByCompanycode(@Param(value="companycode") String companycode);
	/**
	 * 通过companycode模糊查询订单号（餐厅客户端专用）
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectDistinctOrderByCompanycode(@Param(value="companycode") String companycode);
	/**
	 * 通过openid和companycode分组查询订单
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectOddnumberByOpenidandCompanycode(@Param(value="openid")String openid,@Param(value="companycode") String companycode);
	/**
	 * 通过openid分组查询订单
	 * @return
	 */
	public List<Consumerec> selectOddnumberByOpenid(@Param(value="openid")String openid);
	/**
	 * 获得餐饮企业的名录
	 * @param openid
	 * @return
	 */
	public List<String> selectCompanyCodeByOpenid(@Param(value="openid")String openid);
	/**
	 * 通过openid查询订单
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectConsumerecByOpenid(@Param(value="openid")String openid);

	
	/**
	 * 通过openid和企业code 查询是否存在记住发票名称
	 * @param openid
	 * @param companycode
	 * @return
	 */
	public List<Consumerec> selectConsumerByDefaultadd(String openid,String companycode);
	/**
	 * 根据公司id和用户id得到该用户在该餐厅的订单数量
	 * @param openId
	 * @param companyId
	 * @return
	 */
	public int getCountByCompanyidAndOpenid(@Param(value="openid") String openid,@Param(value="companycode") String companycode);
}
