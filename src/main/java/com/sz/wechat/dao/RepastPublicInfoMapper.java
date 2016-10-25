package com.sz.wechat.dao;
import org.springframework.stereotype.Repository;

/**
 * 许可证数据服务接口
 * @author sway
 *
 */

import com.sz.wechat.entity.RepastPublicInfo;
@Repository
public interface RepastPublicInfoMapper {
	
	
	/**
	 * 通过许可证号查询信息
	 * @param repastlicence
	 * @return
	 */
	public RepastPublicInfo getPublicInfo(String repastlicence);

}
