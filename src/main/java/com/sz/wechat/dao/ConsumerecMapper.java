package com.sz.wechat.dao;
import java.util.List;
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

}
