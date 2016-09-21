package com.sz.wechat.dao;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.UserInfo;

/**
 * 用户信息数据服务操作接口
 * @author sway
 *
 */
@Repository
public interface UserInfoMapper {
	
	/**
	 * 获取所有用户
	 * @return 用户集合
	 */
	public List<UserInfo> getUsers();

}
