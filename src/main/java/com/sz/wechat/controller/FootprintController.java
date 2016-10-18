/**
 * 
 */
package com.sz.wechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.Footprint;
import com.sz.wechat.entity.Menu;
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
	
	
	
	
	@RequestMapping(value = "/toFootprint",method = RequestMethod.GET)
	public ModelAndView footprintGet(HttpServletRequest request, HttpServletResponse response){
		String openid = request.getParameter("openid");
		ModelAndView modelAndView = new ModelAndView();
		List<Footprint> footprintlist = this.footprintService.getFootprintByOpenid("oehpaw8_fgOEWtPk0S0gLidH60xg");
		modelAndView.addObject("footprintList", footprintlist);
		modelAndView.setViewName("/footprint");
		return modelAndView;
		
	}
	
	
}
