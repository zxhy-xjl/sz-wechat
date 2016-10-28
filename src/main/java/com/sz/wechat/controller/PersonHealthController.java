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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sz.wechat.entity.CompanyInfo;
import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Evaluate;
import com.sz.wechat.entity.Footprint;
import com.sz.wechat.entity.Grade;
import com.sz.wechat.entity.PersonHealth;
import com.sz.wechat.entity.ScanCode;
import com.sz.wechat.entity.SupervisePunish;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.ComplainService;
import com.sz.wechat.service.EvaluateService;
import com.sz.wechat.service.FootprintService;
import com.sz.wechat.service.PersonHealthService;
import com.sz.wechat.utils.ScanCodeUtils;
import com.sz.wechat.vo.JsonVo;

/***
 * 健康证控制器
 * @author li.lisheng
 *
 */
@Controller
public class PersonHealthController {
	@Autowired
	private PersonHealthService personHealthService;
	@RequestMapping(value = "/personHealthList",method = RequestMethod.GET)
	public ModelAndView personHealthList (HttpServletRequest request, HttpServletResponse response)
	{ 
		String companyCode = request.getParameter("companyCode");
		List<PersonHealth> list = this.personHealthService.getPersonHealthByCompanyCode(companyCode);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("personHealthList", list);
		return modelAndView;
		
	}
	
}
