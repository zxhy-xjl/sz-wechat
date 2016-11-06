package com.sz.wechat.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.basic.DictCommon;
import com.sz.wechat.entity.CompanyInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Evaluate;
import com.sz.wechat.entity.Footprint;
import com.sz.wechat.entity.Grade;
import com.sz.wechat.entity.ScanCode;
import com.sz.wechat.entity.SupervisePunish;
import com.sz.wechat.service.AbnormalService;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.ComplainService;
import com.sz.wechat.service.EvaluateService;
import com.sz.wechat.service.FootprintService;
import com.sz.wechat.utils.RuntimeModel;
import com.sz.wechat.utils.ScanCodeUtils;
import com.sz.wechat.vo.JsonVo;

/***
 * wechat扫一扫控制器
 * @author sway
 *
 */
@Controller
public class ScanCodeController {
	private static Logger logger = LoggerFactory.getLogger(ScanCodeController.class);
	/**
	 * 企业注册数据逻辑层
	 */
	@Autowired
	private CompanyInfoService companyInfoService;
	/**
	 * 投诉反馈数据逻辑层
	 */
	@Autowired
	private ComplainService complainService;
	/**
	 * 足迹数据逻辑层
	 */
	@Autowired
	private FootprintService footPrintService;
	
	/**
	 * 积分数据逻辑层
	 */
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private AbnormalService abnormalService;
	@Autowired
	private RuntimeModel runtimeModel;
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
		Map<String,Object> map = ScanCodeUtils.getScanCode(flag,"");
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
		String code = request.getParameter("code");
		ModelAndView modelAndView = new ModelAndView();
		if(!"".equals(company)){
			String [] companyArr = company.split("●");
			if(null != companyArr && companyArr.length > 0){
				String companyCode = companyArr [companyArr.length-companyArr.length];
				String tablenum = companyArr[companyArr.length-1];
				CompanyInfo companyInfo = companyInfoService.getCompanyByCodeAndType(companyCode,"1");
				//用于异常名录信息检查
				findCTQYAbnormal(request, company, code, companyInfo);
				if(null != companyInfo){
					//增加一次扫描
					addVisitScanToFootPrint(request, companyInfo);
					modelAndView.addObject("CompanyInfo", companyInfo);
					HttpSession session = request.getSession();
					session.setAttribute("code",companyInfo.getCompanycode());
					session.setAttribute("open_code", code);
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


	private void addVisitScanToFootPrint(HttpServletRequest request, CompanyInfo companyInfo) {
		Footprint footPrint = new Footprint();
		footPrint.setCompanycode(companyInfo.getCompanycode());
		footPrint.setCompanyname(companyInfo.getCompanyname());
		String openId = this.runtimeModel.getOpenId(request);
		footPrint.setOpenid(openId);
		footPrint.setPid(UUID.randomUUID().toString());
		String visitTime =(new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date());
		footPrint.setVisittime(visitTime);
		this.footPrintService.doInserFootPrint(footPrint);
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
			CompanyInfo _companyInfo = null;
			for (CompanyInfo companyInfo : companyList) {
				score = 100;
				//资质类
				//营业执照
				if("".equals(companyInfo.getCompanyrecode()) || null == companyInfo.getCompanyrecode()){
					score = score - 30;
				}
				//餐饮服务许可证
				if("".equals(companyInfo.getLicence())|| null == companyInfo.getLicence()){
					score = score - 30;
				}
				//处罚类
				SupervisePunish supervisePunish = new SupervisePunish();
				supervisePunish.setNlawfulcompanyname(companyInfo.getCompanyname());
				list = this.companyInfoService.getSuperviseLikeCompanyName(supervisePunish);
				logger.info("处罚数量:" + list.size());
				if(null != list && list.size() > 0){
					//根据关键字判断
					for (SupervisePunish _supervisePunish : list) {
						String illegalType = _supervisePunish.getPenaltytype();
						//判断是否存在吊销执照
						if(illegalType.indexOf(keyword_3)!=-1 || illegalType.indexOf(keyword_4)!=-1){
							sb.append(illegalType).append(";");
							score = score - 5;
						}else if(illegalType.indexOf(keyword_2)!=-1){
							//停产停业
							sb.append(illegalType).append(";");
							score = score - 4;
						} else 	if(illegalType.indexOf(keyWord_1)!=-1){
							//没收违法所得
							sb.append(illegalType).append(";");
							score = score - 3;
						} else 	if(illegalType.indexOf(keyWord_0)!=-1){
							//罚款
							sb.append(illegalType).append(";");
							score = score - 2;
						} else {
							//警告,其他的都算警告
							sb.append(illegalType).append(";");
							score = score - 1;
						}
					}
				}
				//投诉类
				complaintList = this.companyInfoService.getComplaintByCompanyId(companyInfo.getCompanycode(),Complaint.DISPOSE_STATUS_FANKUI);
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
				int length = 0;
				List<Evaluate> evaluateList = this.evaluateService.getEvaluateByOpenIdAndCompanyCode(companyCode);
				if(null != evaluateList && evaluateList.size()>0){
					for(Evaluate evaluate:evaluateList){
						if(null != evaluate.getEvaluate()&&Integer.parseInt(evaluate.getEvaluate())<3){
							length = length+1;
							gradeStat = Integer.parseInt(evaluate.getEvaluate())+gradeStat;
						}
					}
					if(length>0){
						gradeStat = gradeStat/length;
						gradeStat = grade - gradeStat;
						if(score > gradeStat){
							score = score - gradeStat;
						}else{
							score = 0;
						}
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
					sort = i+1;
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
		int allscore=0;
		String keyWord_0="罚款";
		String keyWord_1="没收";
		String keyword_2="停产停业";
		String keyword_3="吊销执照";
		String keyword_4="暂扣";
		if(!"".equals(companyCode)){
			StringBuffer sb  = new StringBuffer();
			CompanyInfo companyInfo = companyInfoService.getCompanyByCode(companyCode);
			Grade grade = null;
			List<Grade> _mapList = new ArrayList<>();
			List<Grade> _mapList1 = new ArrayList<>();
			HttpSession httpSession =(HttpSession)request.getSession();
			if(null != companyInfo){
				//资质类
				//营业执照
				if("".equals(companyInfo.getCompanyrecode())|| null == companyInfo.getCompanyrecode()){
					score = score - 30;
					allscore  = allscore+ 30;
					grade = new Grade("aptitude","30","营业执照","无","");
					_mapList.add(grade);
				}else{
					grade = new Grade("aptitude","0","<a class='example-image-link' href='http://218.242.124.22:8081/businessCheck/viewLicence_view_20151215.do?attribute13="+companyInfo.getCompanyrecode()+"' data-lightbox='example-set' data-title='"+companyInfo.getCompanyname()+"' style='text-decoration:none'>营业执照</a>","有","");
					_mapList.add(grade);
				}
				//餐饮服务许可证
				if("".equals(companyInfo.getLicence()) || null == companyInfo.getLicence()){
					score = score - 30;
					allscore = allscore+ 30;
					grade = new Grade("aptitude","30","餐饮服务许可证","无","");
					_mapList.add(grade);
				}else{
					grade = new Grade("aptitude","0","<a href='../sz-wechat/getPublicInfo.do?repastlicence="+companyInfo.getLicence()+"'  style='text-decoration:none'>餐饮服务许可证</a>","有","");
					_mapList.add(grade);
				}
				//总分
				grade = new Grade("aptitude","资质"+" -"+String.valueOf(allscore));
				_mapList1.add(grade);
				//健康类
				/*List<PersonHealth> personHealthList = personHealthService.getPersonHealthByCompanyCode(companyInfo.getCompanycode());
				if(null != personHealthList && personHealthList.size() > 0){
					for (PersonHealth personHealth : personHealthList) {
						grade = new Grade("health","0","<a class='example-image-link' href='http://123.57.4.104/sz-wechat/public/healthPhotos/"+personHealth.getHealthpersoncode()+".jpg' data-lightbox='example-set' data-title='"+personHealth.getName()+"' style='text-decoration:none' >健康证-"+personHealth.getName()+"</a>","有","");
						_mapList.add(grade);
					}
				}
				//总分
				grade = new Grade("health","健康证");
				_mapList1.add(grade);*/
				//处罚类
				allscore = 0;
				SupervisePunish supervisePunish = new SupervisePunish();
				supervisePunish.setNlawfulcompanyname(companyInfo.getCompanyname());
				List<SupervisePunish> list = this.companyInfoService.getSuperviseLikeCompanyName(supervisePunish);
				if(null != list && list.size() > 0){
					//根据关键字判断
					for (SupervisePunish _supervisePunish : list) {
						String illegalType = _supervisePunish.getPenaltytype();
						String pentype = _supervisePunish.getPenaltytype();
						//判断是否存在吊销执照
						if(illegalType.indexOf(keyword_3)!=-1 || illegalType.indexOf(keyword_4)!=-1){
							sb.append(illegalType).append(";");
							score = score - 5;
							allscore = allscore + 5;
							grade = new Grade("punish","5","","",pentype);
							_mapList.add(grade);
						}else if(illegalType.indexOf(keyword_2)!=-1){
							//停业
							sb.append(illegalType).append(";");
							score = score - 4;
							allscore = allscore + 4;
							grade = new Grade("punish","4","","",pentype);
							_mapList.add(grade);
						} else if(illegalType.indexOf(keyWord_1)!=-1){
							//违法所得
							sb.append(illegalType).append(";");
							score = score - 3;
							allscore = allscore + 3;
							grade = new Grade("punish","3","","",pentype);
							_mapList.add(grade);
						} else if(illegalType.indexOf(keyWord_0)!=-1){
							//罚款
							sb.append(illegalType).append(";");
							score = score - 2;
							allscore = allscore + 2;
							grade = new Grade("punish","2","","",pentype);
							_mapList.add(grade);
						}else {
							//其他都算警告
							sb.append(illegalType).append(";");
							score = score - 1;
							allscore = allscore + 1;
							grade = new Grade("punish","1","","",pentype);
							_mapList.add(grade);
						}
						
					}
				}
				//总分
				grade = new Grade("punish","处罚"+" -"+String.valueOf(allscore));
				_mapList1.add(grade);
				//投诉类
				int allgrade = 5;
				int gradeStat=0;
				allscore = 0;
				List<Complaint> complaintList = this.companyInfoService.getComplaintByCompanyId(companyCode,Complaint.DISPOSE_STATUS_FANKUI);
				SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
				//投诉
				if(null != complaintList && complaintList.size() > 0){
					for (Complaint complaint : complaintList) {
						if(null != complaint){
							if(score > 2){
								score = score - 2;
								allscore = allscore + 2;
								grade = new Grade("complaint","2","",df.format(new Date()),"<a href='../sz-wechat/lookuserinfo.do?pid="+complaint.getPid()+"&companyname="+companyInfo.getCompanyname()+"' style='text-decoration:none'>"+complaint.getComplaincontent()+"</a>");
								_mapList.add(grade);
							}else{
								score = 0;
								break;
							}
						}
					}
				}
				//投诉总分
				grade = new Grade("complaint","投诉"+" -"+String.valueOf(allscore));
				_mapList1.add(grade);
				//评分
				allscore = 0;
				int length = 0;
				List<Evaluate> evaluateList = this.evaluateService.getEvaluateByOpenIdAndCompanyCode(companyCode);
				if(null != evaluateList && evaluateList.size()>0){
					for(Evaluate evaluate:evaluateList){
						if(null != evaluate.getEvaluate()&&Integer.parseInt(evaluate.getEvaluate())<3){
							length = length + 1;
							gradeStat = Integer.parseInt(evaluate.getEvaluate())+gradeStat;
						}
					}
					if(length>0){
						gradeStat = gradeStat/length;
						gradeStat = allgrade  - gradeStat;
						if(score > gradeStat){
							score = score - gradeStat;
							allscore = allscore + gradeStat;
							grade = new Grade("grade",String.valueOf(gradeStat),"评分","实时","");
							_mapList.add(grade);
						}else{
							score = 0;
						}
					}
					gradeStat=0;
				}
				//评分总分
				grade = new Grade("grade","评分"+" -"+String.valueOf(allscore));
				_mapList1.add(grade);
			}
			 httpSession.setAttribute("_mapList", _mapList);  
			 httpSession.setAttribute("_mapList1", _mapList1); 
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
		String code = request.getParameter("code");
		ModelAndView modelAndView = new ModelAndView();
		if(!"".equals(company)){
			CompanyInfo companyInfo = companyInfoService.getCompanyByCodeAndType(company,"0");
			//用于异常名录信息检查
			findCJQYAbnormal(request, company, code, companyInfo);
			if(null != companyInfo){
				Map<String,Object> map = ScanCodeUtils.getScanCode("2","http://www.haoschoool.com/sz-wechat/checkScanCodeTableWare.do?company="+company);
				modelAndView.addObject("nonceStr", String.valueOf(map.get("nonceStr")));
				modelAndView.addObject("timestamp", String.valueOf(map.get("timestamp")));
				modelAndView.addObject("signature",String.valueOf(map.get("signature")));
				modelAndView.addObject("CompanyInfo", companyInfo);
				List<Evaluate> evaluateList = this.evaluateService.getEvaluateByOpenIdAndCompanyCode(company);
				if(null != evaluateList && evaluateList.size()>0){
					int scort = 0;
					int length = 0;
					for (Evaluate evaluate : evaluateList) {
						if(!"".equals(evaluate.getEvaluate())){
							length = length +1;
							scort = scort + Integer.parseInt(evaluate.getEvaluate());
						}
					}
					scort = scort / length;
					modelAndView.addObject("score", scort);
				}
				HttpSession session = request.getSession();
				session.setAttribute("open_code", code);
				modelAndView.setViewName("/tableware");
			}else{
				modelAndView.addObject("companyCode", company);
				modelAndView.setViewName("/companyError");
			}
		}
		return modelAndView;
	}


	private void findCTQYAbnormal(HttpServletRequest request, String company, String code, CompanyInfo companyInfo) {
		if (companyInfo == null){
			logger.info("扫描餐厅二维码没有找到对应的企业信息，发出一条异常信息");
			String openId = this.runtimeModel.getOpenId(request);
			this.abnormalService.findAbnormal(openId, code, company, true,true);
		} else {
			if (StringUtils.isBlank(companyInfo.getCompanycode()) || StringUtils.isBlank(companyInfo.getLicence())){
				logger.info("扫描餐厅找到对应的企业信息，但是营业执照和许可证有空的，发出一条异常信息");
				String openId = this.runtimeModel.getOpenId(request);
				this.abnormalService.findAbnormal(openId, code, company, StringUtils.isBlank(companyInfo.getCompanycode()), StringUtils.isBlank(companyInfo.getLicence()));
			}
		}
	}
	private void findCJQYAbnormal(HttpServletRequest request, String company, String code, CompanyInfo companyInfo) {
		if (companyInfo == null){
			logger.info("扫描餐具条码没有找到对应的企业信息，发出一条异常信息");
			String openId = this.runtimeModel.getOpenId(request);
			this.abnormalService.findAbnormal(openId, code, company, true,false);
		} else {
			if (StringUtils.isBlank(companyInfo.getCompanycode())){
				logger.info("扫描餐具条码（二维码）找到对应的企业信息，但是营业执照有空的，发出一条异常信息");
				String openId = this.runtimeModel.getOpenId(request);
				this.abnormalService.findAbnormal(openId, code, company, StringUtils.isBlank(companyInfo.getCompanycode()),false);
			}
		}
	}
	
	/**
	 * 跳转至查看评分详细
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toGradeInfo")
	public ModelAndView toGradeInfo(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession){
		String companyname = request.getParameter("companyname");
		String score = request.getParameter("score");
		String ratio = request.getParameter("ratio");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/gradeInfo");
		modelAndView.addObject("companyname", companyname);
		modelAndView.addObject("score",score);
		modelAndView.addObject("ratio",ratio);
		List<Grade> gradeList = (List<Grade>) httpSession.getAttribute("_mapList");
		List<Grade> allgradelist = (List<Grade>)httpSession.getAttribute("_mapList1");
		modelAndView.addObject("gradeList",gradeList);
		modelAndView.addObject("allgradelist",allgradelist);
		return modelAndView;
	}	
	
	/**
	 * 存值openid
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/setOpenId")
	public void setOpenId(HttpServletRequest request, HttpServletResponse response){
		String openid = this.runtimeModel.getOpenId(request);
		HttpSession httpSession =(HttpSession)request.getSession();
		httpSession.setAttribute("openid", openid);
	}
	
	/**
	 * 执行足迹新增
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/doInserFootPrint")
	public void doInserFootPrint(HttpServletRequest request, HttpServletResponse response){
		String openid = this.runtimeModel.getOpenId(request);
		String companyCode = request.getParameter("companCode");
		String companyName = request.getParameter("companyName");
		Footprint footPring = new Footprint();
		footPring.setPid(UUID.randomUUID().toString());
		footPring.setCompanycode(companyCode);
		footPring.setCompanyname(companyName);
		footPring.setOpenid(openid);
		this.footPrintService.doInserFootPrint(footPring);
	}
	
	
	/**
	 * 跳转至评分说明页面
	 */
	@RequestMapping(value = "/toScoreStandar")
	public ModelAndView toScoreStandar(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/scoreStandard");
		return modelAndView;
	}
	
}
