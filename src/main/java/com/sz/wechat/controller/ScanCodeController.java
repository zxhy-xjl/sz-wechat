package com.sz.wechat.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	 * 扫码获取验证（扫桌）
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
	 * 根据餐饮企业评分排名，计算百分比
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "/proportionCompany")
	public JsonVo  proportionCompany(HttpServletRequest request, HttpServletResponse response){
		String companyCode = request.getParameter("companyCode");
		JsonVo  jsonVo = new JsonVo();
		String keyWord="警告";
		String keyWord_0="罚款";
		String keyWord_1="没收";
		String keyword_2="停产停业";
		String keyword_3="吊销执照";
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
				//餐饮服务许可证
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
						gradeStat = Integer.parseInt(complaint.getEvaluate())+gradeStat;
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
		if(null != companyScoreList && companyScoreList.size() > 0){
			/**
			 * 分值排序
			 */
			Collections.sort(companyScoreList,new Comparator<CompanyInfo>(){
				@Override
				public int compare(CompanyInfo o1, CompanyInfo o2) {
					int num1 = o1.getScore();
					int num2 = o2.getScore();
					return num2 - num1;
				}
			});
			int sort = 0;
			double proportion = 0;
			for (int i=0;i <= companyScoreList.size();i++) {
				if (companyCode.equals(companyScoreList.get(i).getCompanycode())){
					sort = i;
					break;
				}
			}
			proportion = Math.floor((Double.parseDouble(String.valueOf((companyScoreList.size()-sort)))/companyScoreList.size())*100);
			jsonVo.setResult(proportion);
		}
		return jsonVo;
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
				int complaintStat=0;
				List<Complaint> complaintList = this.companyInfoService.getComplaintByCompanyId(companyCode);
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
				List<Complaint> complaintScoreList = this.companyInfoService.getComplaintScoreByCompanyId(companyCode);
				if(null != complaintScoreList && complaintScoreList.size()>0){
					for(Complaint complaint:complaintScoreList){
						gradeStat = Integer.parseInt(complaint.getEvaluate())+gradeStat;
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
			}
			jsonVo.setResult(score);
			jsonVo.setMessage(sb.toString());
		}
		return jsonVo;
	}
	
	/**
	 * 扫一扫验证（扫餐具）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/checkScanCodeTableWare")
	public ModelAndView checkScanCodeTableWare(HttpServletRequest request, HttpServletResponse response){
		String company = request.getParameter("company");
		ModelAndView modelAndView = new ModelAndView();
		if(!"".equals(company)){
			CompanyInfo companyInfo = companyInfoService.getCompanyByCode(company);
			if(null != companyInfo){
				modelAndView.addObject("CompanyInfo", companyInfo);
				modelAndView.setViewName("/tableware");
			}else{
				modelAndView.addObject("companyCode", company);
				modelAndView.setViewName("/companyError");
			}
		}
		return modelAndView;
	}
	
	/**
	 * 跳转至查看评分详细
	 * @return
	 */
	@RequestMapping(value = "/toGradeInfo")
	public ModelAndView toGradeInfo(HttpServletRequest request, HttpServletResponse response){
		String companyname = request.getParameter("companyname");
		String score = request.getParameter("score");
		String ratio = request.getParameter("ratio");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/gradeInfo");
		modelAndView.addObject("companyname", companyname);
		modelAndView.addObject("score",score);
		modelAndView.addObject("ratio",ratio);
		return modelAndView;
	}		
	
}
