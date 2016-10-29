package com.sz.wechat.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Consumerec;
import com.sz.wechat.entity.Menu;
import com.sz.wechat.entity.Order;
import com.sz.wechat.entity.UserInfo;
import com.sz.wechat.pagination.DataGridHepler;
import com.sz.wechat.pagination.PageParam;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.ConsumerecService;
import com.sz.wechat.service.MenuService;
import com.sz.wechat.service.UserInfoService;
import com.sz.wechat.utils.RuntimeModel;

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
	@Autowired
	private ConsumerecService consumerecService;
	@Autowired
	private CompanyInfoService companyInfoService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RuntimeModel runtimeModel;
	@RequestMapping(value = "/userInfo",method = RequestMethod.GET)
	public ModelAndView getUsers(HttpServletRequest request,HttpServletResponse response){
		HttpSession ss = (HttpSession)request.getSession();
		String openid=this.runtimeModel.getOpenId(ss);
		//得到所有订单
		List<Order> orderList = this.consumerecService.getOrderList(openid);
		//得到所有的投诉
		List<Complaint> complaintlist = this.companyInfoService.getComplaintByOpenid(openid);
		String companyname;
		
		
		for(int i=0; i<complaintlist.size();i++)
		{
			//consumereclist.get(i)
			companyname =  this.companyInfoService.getCompanyByCode(complaintlist.get(i).getCompanyid()).getCompanyname();
			complaintlist.get(i).setCompanyName(companyname);
			
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderList", orderList);
		modelAndView.addObject("complaintlist", complaintlist);
		modelAndView.setViewName("/userinfo");
		return modelAndView;
	}
	
	@RequestMapping(value = "/lookuserinfo",method = RequestMethod.GET)
	public ModelAndView lookUserinfo(HttpServletRequest request,HttpServletResponse response){
		String pid =  request.getParameter("pid");
		String companyname =  request.getParameter("companyname");
		Complaint complaint = this.companyInfoService.getComplaintInfoByPid(pid);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("complaint", complaint);
		modelAndView.addObject("pid", pid);
		modelAndView.addObject("companyname", companyname);
		modelAndView.setViewName("/complaintinfo");
		return modelAndView;
		
		
	}

	
}
