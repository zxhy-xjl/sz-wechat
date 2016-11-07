package com.sz.wechat.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sz.wechat.dao.UserInfoMapper;
import com.sz.wechat.entity.UserInfo;
/**
 * 用户信息数据逻辑层
 * @author sway
 *
 */
@Service
public class UserInfoService {

	/**
	 * 用户实现接口
	 */
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	
	/**
	 * 获取所有用户信息 
	 * @return 用户信息集合
	 */
	public List<UserInfo> getUsers(){
		
		return this.userInfoMapper.getUsers();
	}
	/**
	 * 根据openid获取用户
	 * @return 用户集合
	 */
	public UserInfo getUsersByOpenid(String openid)
	{
		return this.userInfoMapper.getUsersByOpenid(openid);
	}
	/**
	 * 插入关注用户信息
	 * @param userInfo 
	 * @return
	 */
	public int insertUserInfo(UserInfo userInfo)
	{
		return this.userInfoMapper.insertUserInfo(userInfo);	
	}
    /**
     * 通过openid修改关注用户信息
     * @param userInfo
     */
    public int updateUserInfo(UserInfo userInfo)
    {
    	return this.userInfoMapper.updateUserInfo(userInfo);    	
    }
	
}
