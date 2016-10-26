/**
 * 
 */
package com.sz.wechat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	    String complaintcontent = request.getParameter("complaintcontent");
	    String evaluate = request.getParameter("evaluate");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("companyname", companyname);
		modelAndView.addObject("companycode", companycode);
		modelAndView.addObject("complaintcontent", complaintcontent);
		modelAndView.addObject("evaluate",evaluate);
		modelAndView.setViewName("/docomplaint");
		return modelAndView;
		
	}
	
	/**
	 * 执行投操作
	 * @return
	 */
	@RequestMapping(value = "/doInsertComplaint",method = RequestMethod.GET)
	public ModelAndView toInsertComplainGet (HttpServletRequest request, HttpServletResponse response,HttpSession httpSession){
		String companyname = request.getParameter("companyname");
	    String companycode = request.getParameter("companycode");
	    String complaintcontent = request.getParameter("complaintcontent");
	    String evaluate = request.getParameter("evaluate");
	    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmss");
        String openid = String.valueOf(httpSession.getAttribute("myopenid"));
        Complaint complaint = new Complaint();
        complaint.setCompanyid(companycode);
        complaint.setComplaincontent(complaintcontent);
        complaint.setComplaintime(dateFormater.format(new Date()));
        complaint.setComplaintype("1");
        complaint.setOpenid(openid);
        complaint.setPid(UUID.randomUUID().toString());
        complaint.setDisposestatus("1");
        complaint.setDisposeresult("");
        complaint.setDisposedep("");
        complaint.setDisposetime("");
        complaint.setEvaluate(evaluate);
        this.companyInfoService.insertComplaint(complaint);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pid", complaint.getPid());
		modelAndView.addObject("companyname", companyname);
		modelAndView.setViewName("/complaintsuccess");
		return modelAndView;
		
	}
}
