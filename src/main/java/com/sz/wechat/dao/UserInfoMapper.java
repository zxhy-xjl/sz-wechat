package com.sz.wechat.dao;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.Complaint;
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
	/**
	 * 根据openid获取用户
	 * @return 用户集合
	 */
	public UserInfo getUsersByOpenid(String openid);
	
	/**
	 * 插入关注用户信息
	 * @param userInfo 
	 * @return
	 */
	public int insertUserInfo(UserInfo userInfo);
	
    /**
     * 通过openid修改关注用户信息
     * @param userInfo
     */
    public int updateUserInfo(UserInfo userInfo);

}
