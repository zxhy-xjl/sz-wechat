/**
 * 
 */
package com.sz.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
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
import com.sz.wechat.entity.Order;
import com.sz.wechat.entity.OrderHelper;
import com.sz.wechat.entity.PersonHealth;
import com.sz.wechat.entity.SupervisePunish;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.ComplainService;
import com.sz.wechat.service.ConsumerecService;
import com.sz.wechat.service.FootprintService;
import com.sz.wechat.service.MenuService;
import com.sz.wechat.service.PersonHealthService;
import com.sz.wechat.service.SupervisePunishService;
import com.sz.wechat.utils.RuntimeModel;

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
	@Autowired
    private ComplainService complainService;
	@Autowired
	private RuntimeModel runtimeModel;
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
		String openid = this.runtimeModel.getOpenId(ss);
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
		int superviseCount = this.supervisePunishService.getCountByCompanyName(companyInfo.getCompanyname());
		//投诉数量,已经反馈的投诉
		int complaintcount = this.companyInfoService.getComplaintCountByCompanyId(companyCode, Complaint.DISPOSE_STATUS_FANKUI);
		//获取订单列表
		List<Order> orderList = this.consumerecService.getOrderList(openid, companyCode);
		//订单数量
		int orderCount = orderList.size();
		//订单总额
		int orderTotalMoney = 0;
		for (Order order : orderList) {
			orderTotalMoney += order.getOrderTotalMoney();
		}
		//我在这个餐厅的投诉列表
		List<Complaint> complainList = this.complainService.getComplaintScoreByCompanyIdAndOpenid(companyCode, openid);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("companyInfo", companyInfo);
		modelAndView.addObject("score", score);
		modelAndView.addObject("scanCount", scanCount);
		modelAndView.addObject("star",star);
		modelAndView.addObject("personHealthCount", personHealthCount);
		modelAndView.addObject("superviseCount", superviseCount);
		modelAndView.addObject("complaintcount", complaintcount);
		modelAndView.addObject("face", face);
		
		modelAndView.addObject("orderCount", orderCount);
		modelAndView.addObject("orderTotalMoney", orderTotalMoney);
		modelAndView.addObject("orderList", orderList);
		modelAndView.addObject("complainList", complainList);
		
		
		modelAndView.setViewName("/restaurantinfo");
		
		return modelAndView;
		
	}
}
