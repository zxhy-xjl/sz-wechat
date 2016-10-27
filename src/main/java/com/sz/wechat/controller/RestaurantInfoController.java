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
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.CompanyInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Consumerec;
import com.sz.wechat.entity.Footprint;
import com.sz.wechat.entity.Menu;
import com.sz.wechat.entity.SupervisePunish;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.ConsumerecService;
import com.sz.wechat.service.MenuService;

/**
 * 餐厅详情控制层
 * @author linhd
 * 
 */
@Controller
public class RestaurantInfoController {

	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Autowired
	private ConsumerecService consumerecService;
	
	@Autowired
	private MenuService menuService;
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
		String openid = ss.getAttribute("myopenid").toString();
		String companycode = request.getParameter("companycode");
		String paytime = request.getParameter("paytime");
		String complaintpid = request.getParameter("complaintpid");
		String complainflag = request.getParameter("complainflag");
		String pid = request.getParameter("pid");
		//Complaint complaintforjsp = this.companyInfoService.getComplaintInfoByPid(complaintpid);
		
		List<Consumerec> consumelist2 = this.consumerecService.selectConsumerecByOpenidandCompanycode(openid, companycode);
		//计算历次总价
		float totalprice=0;
		for(int u=0;u<consumelist2.size();u++)
		{
			Menu menu = this.menuService.getMenuByMenuId(consumelist2.get(u).getMenuid());
			if(menu.getPrice()!=null)
				totalprice+=Float.parseFloat(menu.getPrice())*Float.parseFloat(consumelist2.get(u).getBuynum());
				
		}
		//计算单次总价
		float price=0;
		List<Consumerec> consumelist = this.consumerecService.selectConsumerecByPaytime(paytime, companycode);
		for(int i=0;i<consumelist.size();i++)
		{
			Menu menu = this.menuService.getMenuByMenuId(consumelist.get(i).getMenuid());
			
			if(menu.getPrice()!=null)
			price+=Float.parseFloat(menu.getPrice())*Float.parseFloat(consumelist.get(i).getBuynum());
			
		}
		
		//计算餐厅评分
		CompanyInfo companyInfo2 = companyInfoService.getCompanyByCode(companycode);
		ModelAndView modelAndView = new ModelAndView();
		String keyWord="警告";
		String keyWord_0="罚款";
		String keyWord_1="没收";
		String keyword_2="停产停业";
		String keyword_3="吊销执照";
		String keyword_4="暂扣";
		List<CompanyInfo> companyList = this.companyInfoService.getCompanyInfo();
		List<CompanyInfo> companyScoreList = new ArrayList<>();
		if(null != companyList && companyList.size() > 0){
		int score = 100;
		int grade = 5;//评分
		int gradeStat=0;
		int complaintStat=0;
		StringBuffer sb  = new StringBuffer();
		List<SupervisePunish> list = null;//存储企业处罚信息
		List<Complaint> complaintList = null;//存储企业投诉信息
		List<Complaint> complaintScoreList = null;//存储企业投诉评分信息
		CompanyInfo _companyInfo = null;
		for (CompanyInfo companyInfo : companyList) {
			score = 100;
			//资质类
			//营业执照
			if("".equals(companyInfo.getCompanyrecode())|| null == companyInfo.getCompanyrecode()){
				score = score - 30;
			}
			//餐饮服务许可证
			if("".equals(companyInfo.getLicence()) || null == companyInfo.getLicence()){
				score = score - 30;
			}
			//处罚类
			SupervisePunish supervisePunish = new SupervisePunish();
			supervisePunish.setNlawfulcompanyname(companyInfo.getCompanyname());
			list = this.companyInfoService.getSuperviseLikeCompanyName(supervisePunish);
			if(null != list && list.size() > 0){
				//根据关键字判断
				for (SupervisePunish _supervisePunish : list) {
					String illegalType = _supervisePunish.getPenaltytype();
					//判断是否存在吊销执照
					if(illegalType.indexOf(keyword_3)!=-1){
						sb.append(illegalType).append(";");
						score = score - 5;
					}else if(illegalType.indexOf(keyword_4)!=-1){
						sb.append(illegalType).append(";");
						score = score - 5;
					}
					//停产停业
					if(illegalType.indexOf(keyword_2)!=-1){
						sb.append(illegalType).append(";");
						score = score - 4;
					}
					//没收违法所得
					if(illegalType.indexOf(keyWord_1)!=-1){
						sb.append(illegalType).append(";");
						score = score - 3;
					}
					//罚款
					if(illegalType.indexOf(keyWord_0)!=-1){
						sb.append(illegalType).append(";");
						score = score - 2;
					}
					//警告
					if(illegalType.indexOf(keyWord)!=-1){
						sb.append(illegalType).append(";");
						score = score - 1;
					}
				}
			}
			//投诉类
			complaintList = this.companyInfoService.getComplaintByCompanyId(companyInfo.getCompanycode());
			//投诉
			if(null != complaintList && complaintList.size() > 0){
				for (Complaint complaint : complaintList) {
					if(null != complaint){
						complaintStat = complaintStat + 2;
					}
				}
				if(score > complaintStat){
					score = score - complaintStat;
				}else{
					score = 0;
				}
				complaintStat = 0;
			}
			//评分
			complaintScoreList = this.companyInfoService.getComplaintScoreByCompanyId(companyInfo.getCompanycode());
			if(null != complaintScoreList && complaintScoreList.size()>0){
				for(Complaint complaint:complaintScoreList){
					if(complaint.getEvaluate()!=null)
					gradeStat = Integer.parseInt(complaint.getEvaluate())+gradeStat;
					else
						gradeStat=0;
				}
				gradeStat = gradeStat/complaintScoreList.size();
				gradeStat = grade - gradeStat;
				if(score > gradeStat){
					score = score - gradeStat;
				}else{
					score = 0;
				}
				gradeStat=0;
			}
			_companyInfo = new CompanyInfo();
			_companyInfo.setCompanycode(companyInfo.getCompanycode());
			_companyInfo.setScore(score);
			companyScoreList.add(_companyInfo);
		}
	}
		for (int i=0;i <= companyScoreList.size();i++) {
			if (companycode.equals(companyScoreList.get(i).getCompanycode())){
				companyInfo2.setScore(companyScoreList.get(i).getScore());
				break;
			}
		}
		//CompanyInfo companyInfo = companyInfoService.getCompanyByCode(companycode);
		modelAndView.addObject("CompanyInfo", companyInfo2);
		modelAndView.addObject("oddnumber", consumelist.get(0).getOddnumber());
		modelAndView.addObject("price", price);
		modelAndView.addObject("totalprice", totalprice);
		modelAndView.addObject("paytime", paytime);
		modelAndView.addObject("complaintpid", complaintpid);
		modelAndView.addObject("complainflag", complainflag);
		modelAndView.addObject("pid", pid);
		//modelAndView.addObject("complaintforjsp", complaintforjsp);
		modelAndView.setViewName("/restaurantinfo");
		
		return modelAndView;
		
	}
}
