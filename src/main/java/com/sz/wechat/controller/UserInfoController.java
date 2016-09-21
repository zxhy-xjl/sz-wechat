package com.sz.wechat.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sz.wechat.entity.UserInfo;
import com.sz.wechat.service.UserInfoService;

/**
 * 用户信息逻辑控制器
 * @author sway
 *
 */
@Controller
public class UserInfoController {
	protected final Logger log = LoggerFactory.getLogger(UserInfoController.class);
	
	/**
	 * 
	 */
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 *获取所有用户
	 * @return 用户信息集合
	 */
	@RequestMapping(value = "/userInfo/users")
	public List<UserInfo> getUsers(){
		return this.userInfoService.getUsers();
	}
	
}
