package com.sz.wechat.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.CompanyInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.ScanCode;
import com.sz.wechat.entity.SupervisePunish;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.utils.ScanCodeUtils;
import com.sz.wechat.vo.JsonVo;

/***
 * wechat扫一扫控制器
 * @author sway
 *
 */
@Controller
public class ScanCodeController {

	/**
	 * 企业注册数据逻辑层
	 */
	@Autowired
	private CompanyInfoService companyInfoService;
	/**
	 * 扫一扫
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/scanCode/sign")
	public JsonVo  sign(HttpServletRequest request, HttpServletResponse response){
		String flag = request.getParameter("flag");
		Map<String,Object> map = ScanCodeUtils.getScanCode(flag);
		JsonVo  jsonVo = new JsonVo();
		ScanCode scanCode = null;
		if(null != map){ 
			scanCode = new ScanCode();
			scanCode.setTicket(String.valueOf(map.get("jsapi_ticket")));
			scanCode.setNonce(String.valueOf(map.get("nonceStr")));
			scanCode.setTimestamp(String.valueOf(map.get("timestamp")));
			scanCode.setSignature(String.valueOf(map.get("signature")));
		}
		jsonVo.setResult(scanCode);
		return jsonVo;
	}
	
	
	/**
	 * 扫码获取验证
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/checkScanCode")
	public ModelAndView  checkScanCode(HttpServletRequest request, HttpServletResponse response){
		String company = request.getParameter("company");
		ModelAndView modelAndView = new ModelAndView();
		if(!"".equals(company)){
			String [] companyArr = company.split("●");
			if(null != companyArr && companyArr.length > 0){
				String companyCode = companyArr [companyArr.length-companyArr.length];
				String tablenum = companyArr[companyArr.length-1];
				CompanyInfo companyInfo = companyInfoService.getCompanyByCode(companyCode);
				if(null != companyInfo){
					modelAndView.addObject("CompanyInfo", companyInfo);
					modelAndView.addObject("tableNum",tablenum);
					modelAndView.setViewName("/scanCodeFeedBack");
				}else{
					modelAndView.addObject("companyCode", companyCode);
					modelAndView.setViewName("/companyError");
				}
			}
		}
		return modelAndView;
	}
	
	
	/**
	 * 评分
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "/superviseScore")
	public JsonVo checkScore(HttpServletRequest request, HttpServletResponse response){
		String companyCode = request.getParameter("companyCode");
		JsonVo  jsonVo = new JsonVo();
		int score = 100;
		String keyWord="警告";
		String keyWord_0="罚款";
		String keyWord_1="没收";
		String keyword_2="停产停业";
		String keyword_3="吊销执照";
		if(!"".equals(companyCode)){
			StringBuffer sb  = new StringBuffer();
			CompanyInfo companyInfo = companyInfoService.getCompanyByCode(companyCode);
			if(null != companyInfo){
				//资质类
				//营业执照
				//餐饮服务许可证
				
				//处罚类
				SupervisePunish supervisePunish = new SupervisePunish();
				supervisePunish.setNlawfulcompanyname(companyInfo.getCompanyname());
				List<SupervisePunish> list = this.companyInfoService.getSuperviseLikeCompanyName(supervisePunish);
				if(null != list && list.size() > 0){
					//根据关键字判断
					for (SupervisePunish _supervisePunish : list) {
						String illegalType = _supervisePunish.getPenaltytype();
						//判断是否存在吊销执照
						if(illegalType.indexOf(keyword_3)!=-1){
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
				int grade = 5;
				int gradeStat=0;
				//投诉建议 -- 店铺
				List<Complaint> complaintList = this.companyInfoService.getComplaintBycomplainTypeAndcompanyId(companyCode,"1");
				if(null != complaintList && complaintList.size()>0){
					for(Complaint complaint:complaintList){
						gradeStat = Integer.parseInt(complaint.getEvaluate())+gradeStat;
					}
					gradeStat = gradeStat/complaintList.size();
					gradeStat = grade - gradeStat;
					score = score - gradeStat;
					gradeStat=0;
				}
				//投诉建议 -- 卫生
				complaintList = this.companyInfoService.getComplaintBycomplainTypeAndcompanyId(companyCode,"2");
				if(null != complaintList && complaintList.size()>0){
					for(Complaint complaint:complaintList){
						gradeStat = Integer.parseInt(complaint.getEvaluate())+gradeStat;
					}
					gradeStat = gradeStat/complaintList.size();
					gradeStat = grade - gradeStat;
					score = score - gradeStat;
					gradeStat=0;
				}
				//投诉建议 -- 服务
				complaintList = this.companyInfoService.getComplaintBycomplainTypeAndcompanyId(companyCode,"3");
				if(null != complaintList && complaintList.size()>0){
					for(Complaint complaint:complaintList){
						gradeStat = Integer.parseInt(complaint.getEvaluate())+gradeStat;
					}
					gradeStat = gradeStat/complaintList.size();
					gradeStat = grade - gradeStat;
					score = score - gradeStat;
					gradeStat=0;
				}
			}
			jsonVo.setResult(score);
			jsonVo.setMessage(sb.toString());
		}
		return jsonVo;
	}
}
