package com.sz.wechat.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sz.wechat.entity.UserInfo;
import com.sz.wechat.pagination.DataGridHepler;
import com.sz.wechat.pagination.PageParam;
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
	 * 用户信息数据逻辑层
	 */
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 *获取所有用户
	 * @return 用户信息集合
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "/userInfo")
	public ModelMap getUsers(HttpServletRequest request, ModelMap modelMap){
		PageParam page = DataGridHepler.parseRequest(request);
		log.debug("page:" + page.getPage());
		log.debug("pageSize:" + page.getPageSize());
		List<UserInfo> listUserInfo = this.userInfoService.getUsers(page.getPage(), page.getPageSize());
		long total = new PageInfo(listUserInfo).getTotal();
		log.debug("本次获取的大小为:" + listUserInfo.size());
		log.debug("total:" + total);
		StringBuilder sb = null;
		if(null != listUserInfo && listUserInfo.size() > 0){
			sb = new StringBuilder();
			for (UserInfo userInfo : listUserInfo) {
				sb.append("<li class=\"mui-table-view-cell mui-media\">");
				sb.append("	<div class=\"mui-media-body\">");
				sb.append("		<h4 class=\"mui-ellipsis\">" +userInfo.getNickname()+ "</h4>");
				sb.append("	</div>");
				sb.append("</li>");
			}
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", total);
		json.put("html", sb.toString());
		modelMap.addAttribute("result", json);
		return modelMap;
	}
	
	 
	
}
