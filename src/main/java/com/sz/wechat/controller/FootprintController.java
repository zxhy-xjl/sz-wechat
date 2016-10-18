/**
 * 
 */
package com.sz.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	
	
	
	@RequestMapping(value = "/insertLog1",method = RequestMethod.POST)
	public ModelAndView footprintGet(){
		
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
		
	}
	
	
}
