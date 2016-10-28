/**
 * 
 */
package com.sz.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.CompanyInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Footprint;
import com.sz.wechat.entity.Grade;
import com.sz.wechat.entity.Menu;
import com.sz.wechat.entity.SupervisePunish;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.FootprintService;

/**
 * 足迹信息控制层
 * @author linhd
 *
 */
@Controller
public class FootprintController {

	
	@Autowired
	private FootprintService footprintService;
	
	
	@Autowired
	private CompanyInfoService companyInfoService;
	

	@RequestMapping(value = "/toFootprint",method = RequestMethod.GET)
	public ModelAndView footprintGet(HttpServletRequest request, HttpServletResponse response){
		//获取到用户的id
		String openid = request.getParameter("openid");
		openid="oehpaw8_fgOEWtPk0S0gLidH60xg";
		//存入session，以便其他地方直接从session中获取openid
		HttpSession ss = (HttpSession)request.getSession();
        ss.setAttribute("openid",openid);
        
		ModelAndView modelAndView = new ModelAndView();
		//根据openid获取足迹列表
		List<Footprint> footprintlist = this.footprintService.getDisFootprintByOpenid(openid);	
				
		modelAndView.addObject("footprintList", footprintlist);
		modelAndView.setViewName("/footprint");
	 
		return modelAndView;
		
	}
	
	
	
	public void doInsertFootPrint(HttpServletRequest request, HttpServletResponse response){
		
		
	}
	
	
}
