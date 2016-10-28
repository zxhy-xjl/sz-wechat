/**
 * 
 */
package com.sz.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.CompanyInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Consumerec;
import com.sz.wechat.entity.Footprint;
import com.sz.wechat.entity.Menu;
import com.sz.wechat.entity.OrderHelper;
import com.sz.wechat.entity.PersonHealth;
import com.sz.wechat.entity.SupervisePunish;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.ConsumerecService;
import com.sz.wechat.service.FootprintService;
import com.sz.wechat.service.MenuService;
import com.sz.wechat.service.PersonHealthService;
import com.sz.wechat.service.SupervisePunishService;

/**
 * 餐厅详情控制层
 * @author linhd
 * 
 */
@Controller
public class RestaurantInfoController {
	private static Log log = LogFactory.getLog(RestaurantInfoController.class);
	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Autowired
	private ConsumerecService consumerecService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private FootprintService footprintService;
	@Autowired
	private PersonHealthService personHealthService;
	@Autowired
	private SupervisePunishService supervisePunishService;
	/**
	 * 餐厅详情页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toRestaurant",method = RequestMethod.GET)
	public ModelAndView restartantinfoGet (HttpServletRequest request, HttpServletResponse response)
	{ 
		HttpSession ss = (HttpSession)request.getSession();
		//String openid = ss.getAttribute("openid").toString();
		String openid = "oehpaw8_fgOEWtPk0S0gLidH60xg";
		String companyCode = request.getParameter("companycode");
		CompanyInfo companyInfo = this.companyInfoService.getCompanyByCode(companyCode);
		//综合得分
		int score = this.companyInfoService.getScore(companyCode);
		log.debug("score:" + score);
		//累计扫描次数
		int scanCount = this.footprintService.getCountByCompany(companyCode);
		log.debug("scanCount:" + scanCount);
		//企业当前是几颗星
		int star = this.companyInfoService.getEvaluate(companyCode);
		log.debug("star:" + star);
		//健康证
		List<PersonHealth> personHealthList = personHealthService.getPersonHealthByCompanyCode(companyInfo.getCompanycode());
		int personHealthCount = 0;
		if (personHealthList != null){
			personHealthCount = personHealthList.size();
		}
		//脸谱
		String face = this.companyInfoService.getFace(companyCode);
		log.debug("face:" + face);
		//处罚数量
		System.out.println("companyInfo.getCompanyname():" + companyInfo.getCompanyname());
		int superviseCount = this.supervisePunishService.getCountByCompanyName(companyInfo.getCompanyname());
		System.out.println("superViseCount:" + superviseCount);
		//投诉数量
		int complaintcount = this.companyInfoService.getComplaintCountByCompanyIdAndOpenid(companyCode, openid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("companyInfo", companyInfo);
		modelAndView.addObject("score", score);
		modelAndView.addObject("scanCount", scanCount);
		modelAndView.addObject("star",star);
		modelAndView.addObject("personHealthCount", personHealthCount);
		modelAndView.addObject("superviseCount", superviseCount);
		modelAndView.addObject("complaintcount", complaintcount);
		modelAndView.addObject("face", face);
		
		modelAndView.addObject("totalprice", 40);
		modelAndView.setViewName("/restaurantinfo");
		
		return modelAndView;
		
	}
}
