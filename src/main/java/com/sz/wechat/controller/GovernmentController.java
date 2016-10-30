/**
 * 
 */
package com.sz.wechat.controller;

import java.io.IOException;
import java.text.DateFormat;
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
	@Autowired
	private CompanyInfoService companyInfoService;
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
		modelAndView.setViewName("forward:/manager/govcomplaint.jsp");
		return modelAndView;
		
	}
	
	/**
	 * 政府端执行投诉详情操作
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/governmentdetails")
	public ModelAndView ComplaindetailsGet (HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pid = request.getParameter("pid");
		String companyname = request.getParameter("companyname");
		Complaint complaint = this.companyInfoService.getComplaintInfoByPid(pid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("complaint", complaint);
		modelAndView.addObject("companyname", companyname);
		//log.debug(complainlist.get(0).getComplaincontent());
		//modelAndView.addObject("companyname", companyname);
		modelAndView.setViewName("forward:/manager/govcomplaintdetails.jsp");
		return modelAndView;
		
	}
	
	
	/**
	 * 政府端执行投诉详情操作
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/updategovdetails")
	public ModelAndView UpdatedetailsGet (HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pid = request.getParameter("pid");
		String status = request.getParameter("status");
		if(status==null )
			status="";
		String feedback = request.getParameter("feedback");
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String time=format.format(date);
		Complaint complaint = new Complaint();
		complaint.setPid(pid);
		complaint.setDisposestatus(status);
		complaint.setDisposetime(time);
		complaint.setDisposeresult(feedback);
		if(status.equals("4"))
		this.complainService.updateStatusandFeedByPid(complaint);
		else
			this.complainService.updateStatusByPid(complaint);
		
		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("complaint", complaint);
		
		//log.debug(complainlist.get(0).getComplaincontent());
		//modelAndView.addObject("companyname", companyname);
		modelAndView.setViewName("redirect:/government.do");
		return modelAndView;
		
	}
	
	
	
	
}
