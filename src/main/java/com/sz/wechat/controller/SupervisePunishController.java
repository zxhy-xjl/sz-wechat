package com.sz.wechat.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.SupervisePunish;
import com.sz.wechat.service.SupervisePunishService;

/**
 * 监管处罚控制器
 * @author sway
 */
@Controller
public class SupervisePunishController {

	/**
	 * 监管处罚数据逻辑层
	 */
	@Autowired
	private SupervisePunishService supervisorPunishService;
	
	
	/**
	 * 根据企业名称获取企业处罚信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getSuperviseByCompanyName")
	public ModelAndView getSuperviseByCompanyName(HttpServletRequest request, HttpServletResponse response){
		String nlawfulcompanyname = request.getParameter("companyname");
		ModelAndView modelAndView = new ModelAndView();
		SupervisePunish supervisePunish = new SupervisePunish();
		supervisePunish.setNlawfulcompanyname(nlawfulcompanyname);
		List<SupervisePunish> _supervisePunishList = this.supervisorPunishService.getSuperviseByCompanyName(supervisePunish);
		modelAndView.addObject("SupervisePunishList",_supervisePunishList);
		modelAndView.addObject("chntitle","行政处罚信息");
		modelAndView.setViewName("/punishInfo");
		return modelAndView;
	}
	
	
	
	
	
	
}
