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
	 * 
	 * @param list
	 * @return
	 */
	public int batchInsertConsumerec(List<Consumerec> list){
		 return this.consumerecMapper.batchInsertConsumerec(list);
	}

}
