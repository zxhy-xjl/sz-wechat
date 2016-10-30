package com.sz.wechat.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.wechat.dao.CompanyInfoMapper;
import com.sz.wechat.dao.ConsumerecMapper;
import com.sz.wechat.dao.MenuMapper;
import com.sz.wechat.entity.Consumerec;
import com.sz.wechat.entity.Menu;
/**
 * 订单记录数据逻辑层
 * @author sway
 */
import com.sz.wechat.entity.Order;
@Service
public class ConsumerecService {
	private static Logger logger = LoggerFactory.getLogger(ConsumerecService.class);
	/**
	 * 订单记录数据服务接口
	 */
	@Autowired
	private ConsumerecMapper consumerecMapper;
	/**
	 * 菜单服务接口
	 */
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
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
	public List<Order> getOrderList(String openId){
		List<Consumerec> oddNumberList = this.consumerecMapper.selectOddnumberByOpenid(openId);
		return this.toOrderList(oddNumberList);
	}
	/**
	 * 根据下单人和餐厅得到订单列表
	 * @param openId
	 * @param companyCode
	 * @return
	 */
	public List<Order> getOrderList(String openId, String companyCode){
		List<Consumerec> oddNumberList = this.consumerecMapper.selectOddnumberByOpenidandCompanycode(openId, companyCode);
		return toOrderList(oddNumberList);
		
	}

	private List<Order> toOrderList(List<Consumerec> oddNumberList) {
		List<Order> orderList = new ArrayList<>();
		for (Consumerec consumerecOddNumber : oddNumberList) {
			Order order = new Order();
			order.setOrderNo(consumerecOddNumber.getOddnumber());
			List<Consumerec> consumerecList = this.consumerecMapper.selectConsumerecByOddNumber(order.getOrderNo());
			//先把订单时间、支付状态设置下，这个数据只要获取到第一条记录即可
			if (consumerecList != null && !consumerecList.isEmpty()){
				Consumerec consumerec = consumerecList.get(0);
				order.setOpenId(consumerec.getOpenid());
				order.setCompanyCode(consumerec.getCompanycode());
				String companyName = this.companyInfoMapper.getCompanyByCode(order.getCompanyCode()).getCompanyname();
				order.setCompanyName(companyName);
				order.setOrderDate(consumerec.getOddTime());
				if (consumerec.getPaytime()==null){
					order.setOrderStatus("待支付");
				} else {
					order.setOrderStatus("已支付");
				}
			}
			//遍历所有的记录，计算总金额
			for (Consumerec consumerec : consumerecList) {
				order.setOrderCount(order.getOrderCount()+NumberUtils.toInt(consumerec.getBuynum()));
				Menu menu = this.menuMapper.getMenuByMenuId(consumerec.getMenuid());
				int money = NumberUtils.toInt(consumerec.getBuynum()) * NumberUtils.toInt(menu.getPrice());
				order.setOrderTotalMoney(order.getOrderTotalMoney() + money);
			}
			orderList.add(order);
		}
		return orderList;
	}
	
	/**
	 * 通过companycode模糊查询订单（餐厅客户端专用）
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectConsumerecByCompanycode(@Param(value="companycode") String companycode)
	{
		return this.consumerecMapper.selectConsumerecByCompanycode(companycode);
	}
	/**
	 * 通过companycode模糊查询订单号（餐厅客户端专用）
	 * @param paytime 下单时间
	 * @return
	 */
	public List<Consumerec> selectDistinctOrderByCompanycode(@Param(value="companycode") String companycode)
	{
		return this.consumerecMapper.selectDistinctOrderByCompanycode(companycode);
		
	}

}
