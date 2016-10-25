/**
 * 
 */
package com.sz.wechat.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.Complaint;
import com.sz.wechat.service.CompanyInfoService;

/**
 * 投诉控制层
 * @author linhd
 *
 */
@Controller
public class ComplainController {
    @Autowired
	private CompanyInfoService companyInfoService;
	@RequestMapping(value = "/toComplain",method = RequestMethod.GET)
	public ModelAndView toComplainGet (HttpServletRequest request, HttpServletResponse response)
	{   String companyname = request.getParameter("companyname");
	    String companycode = request.getParameter("companycode");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("companyname", companyname);
		modelAndView.addObject("companycode", companycode);
		modelAndView.setViewName("/docomplaint");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/toInsertComplaint",method = RequestMethod.GET)
	public ModelAndView toInsertComplainGet (HttpServletRequest request, HttpServletResponse response)
	{   String companyname = request.getParameter("companyname");
	    String companycode = request.getParameter("companycode");
	    String complaintcontent = request.getParameter("complaintcontent");
	    HttpSession ss = (HttpSession)request.getSession();
        String openid = ss.getAttribute("myopenid").toString();
        Complaint complaint = new Complaint();
        complaint.setCompanyid(companycode);
        complaint.setComplaincontent(complaintcontent);
        complaint.setComplaintime("");
        complaint.setComplaintype("1");
        complaint.setOpenid(openid);
        complaint.setPid(UUID.randomUUID().toString());
        complaint.setDisposestatus("1");
        complaint.setDisposeresult("");
        complaint.setDisposedep("");
        complaint.setDisposetime("");
        complaint.setEvaluate("3");
       
        this.companyInfoService.insertComplaint(complaint);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pid", complaint.getPid());
		modelAndView.addObject("companyname", companyname);
		modelAndView.setViewName("/complaintsuccess");
		return modelAndView;
		
	}
}
