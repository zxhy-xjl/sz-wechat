package com.sz.wechat.controller;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sz.wechat.entity.UserInfo;

public class UserInfoTest {

	private UserInfoController userInfoController = new UserInfoController();
	
	@Test
	public void test(){
		List<UserInfo> list = this.userInfoController.getUsers();
		System.out.println(list.size());
	}
}
