/**
 * 
 */
package com.sz.wechat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Footprint;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.ComplainService;
import com.sz.wechat.service.FootprintService;

/**
 * @author linhd
 *
 */
@Controller
public class GovernmentController {
	private static Log log = LogFactory.getLog(GovernmentController.class);

	@Autowired
	private FootprintService footprintService;
	@Autowired
	private ComplainService complainService;
	
	/**
	 * 政府端执行投诉的查询操作
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/government")
	public ModelAndView selectComplainGet (HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		List<Complaint> complainlist = this.complainService.getMoreComplaintInfo();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("complainlist", complainlist);
		log.debug(complainlist.get(0).getComplaincontent());
		//modelAndView.addObject("companyname", companyname);
		modelAndView.setViewName("/govcomplaint");
		return modelAndView;
		
	}
	
}
