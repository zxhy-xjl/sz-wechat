package com.sz.wechat.service;
import java.util.List;
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
	 * 修改支付状态
	 * @param consumerec
	 * @return
	 */
	public int updatePayByOddNumber(Consumerec consumerec){
		return this.consumerecMapper.updatePayByOddNumber(consumerec);
	}

}
