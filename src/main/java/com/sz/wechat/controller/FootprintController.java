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
		String openid = request.getParameter("openid");
		HttpSession ss = (HttpSession)request.getSession();
        ss.setAttribute("myopenid","oehpaw8_fgOEWtPk0S0gLidH60xg");
		ModelAndView modelAndView = new ModelAndView();
		List<Footprint> footprintlist = this.footprintService.getFootprintByOpenid("oehpaw8_fgOEWtPk0S0gLidH60xg");
		
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
		for (int i=0;i < footprintlist.size();i++) {
			
			for(int u=0;u<companyScoreList.size();u++)
			if (footprintlist.get(i).getCompanycode().equals(companyScoreList.get(u).getCompanycode())){
				footprintlist.get(i).setScore(String.valueOf(companyScoreList.get(u).getScore()));
				continue;
			}
		}
		
		modelAndView.addObject("footprintList", footprintlist);
		modelAndView.setViewName("/footprint");
	 
		return modelAndView;
		
	}
	
	
	
	public void doInsertFootPrint(HttpServletRequest request, HttpServletResponse response){
		
		
	}
	
	
}
