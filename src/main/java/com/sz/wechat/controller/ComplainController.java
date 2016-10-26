/**
 * 
 */
package com.sz.wechat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.sz.wechat.service.ComplainService;

/**
 * 投诉控制层
 * @author linhd
 *
 */
@Controller
public class ComplainController {
    @Autowired
	private CompanyInfoService companyInfoService;
    
    /**
     * 投诉数据逻辑层
     */
    @Autowired
    private ComplainService complainService;
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
	@RequestMapping(value = "/doInsertComplaint")
	public ModelAndView toInsertComplainGet (HttpServletRequest request, HttpServletResponse response,HttpSession httpSession){
		String companyname = request.getParameter("companyname");
	    String companycode = request.getParameter("companycode");
	    String complaintcontent = request.getParameter("complaintcontent");
	    String evaluate = request.getParameter("evaluate");
	    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmss");
        String openid = String.valueOf(httpSession.getAttribute("myopenid"));
        if("".equals(openid)){
        	openid = String.valueOf(httpSession.getAttribute("openid"));
        }
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

	/**
	 * 通过openID获取用户投诉信息
	 * @param request
	 * @param response
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/getComplaintByOpenid")
	public ModelAndView getComplaintByOpenid(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		String openid = String.valueOf(httpSession.getAttribute("openid"));//得到session中的openID
		String companyCode = String.valueOf(request.getParameter("companyCode"));
		String companyName = String.valueOf(request.getParameter("companyName"));
		List<Complaint> complaintList = this.complainService.getComplaintScoreByCompanyIdAndOpenid(companyCode,"oehpaw8_fgOEWtPk0S0gLidH60xg");
		modelAndView.addObject("complaintList", complaintList);
		modelAndView.addObject("companyName", companyName);
		modelAndView.setViewName("/complaintList");
		return modelAndView;
	}
}
