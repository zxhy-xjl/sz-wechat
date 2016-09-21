package com.sz.wechat.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
