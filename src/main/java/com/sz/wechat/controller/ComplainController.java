/**
 * 
 */
package com.sz.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 投诉控制层
 * @author linhd
 *
 */
@Controller
public class ComplainController {

	@RequestMapping(value = "/toComplain",method = RequestMethod.GET)
	public ModelAndView toComplainGet (HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/docomplaint");
		return modelAndView;
		
	}
	
	
}
