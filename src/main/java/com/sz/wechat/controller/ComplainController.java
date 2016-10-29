/**
 * 
 */
package com.sz.wechat.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.Complaint;
import com.sz.wechat.entity.Footprint;
import com.sz.wechat.service.CompanyInfoService;
import com.sz.wechat.service.ComplainService;
import com.sz.wechat.service.FootprintService;

/**
 * 投诉控制层
 * @author linhd
 *
 */
@Controller
public class ComplainController {
    @Autowired
	private CompanyInfoService companyInfoService;
    @Autowired
    private FootprintService footprintService;
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
	    String footprintpid = request.getParameter("footprintpid");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("companyname", companyname);
		modelAndView.addObject("companycode", companycode);
		modelAndView.addObject("complaintcontent", complaintcontent);
		modelAndView.addObject("evaluate",evaluate);
		modelAndView.addObject("footprintpid",footprintpid);
		modelAndView.setViewName("/docomplaint");
		return modelAndView;
		
	}
	
	/**
	 * 执行投诉的插入操作
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/doInsertComplaint")
	public ModelAndView toInsertComplainGet (HttpServletRequest request, HttpServletResponse response,@RequestParam MultipartFile camera) throws IOException{
		String companyname = request.getParameter("companyname");
	    String companycode = request.getParameter("companycode");
	    String complaintcontent = request.getParameter("complaintcontent");
	    String footprintpid = request.getParameter("footprintpid");
	    byte[] complainphoto = camera.getBytes();
	    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmss");
	    HttpSession httpSession = (HttpSession)request.getSession();
        String openid = String.valueOf(httpSession.getAttribute("openid"));
        Complaint complaint = new Complaint();
        complaint.setCompanyid(companycode);
        complaint.setComplaincontent(complaintcontent);
        complaint.setComplaintime(dateFormater.format(new Date()));
        complaint.setOpenid(openid);
        complaint.setPid(UUID.randomUUID().toString());
        //1是投诉，2是受理，3是处理，4是反馈
        complaint.setDisposestatus("1");
        complaint.setDisposeresult("");
        complaint.setDisposedep("");
        complaint.setDisposetime("");
        complaint.setComplaintype("1");
        //投诉只保存一个图片
        complaint.setComplainphoto(complainphoto);
        this.companyInfoService.insertComplaint(complaint);
        //更新足迹投诉状态
        Footprint footprint = new Footprint();
        footprint.setPid(footprintpid);
        //设置该足迹有投诉
        footprint.setComplaintflag("1");
    	this.footprintService.updatePrintByComplaintpidandFlag(footprint);
        
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
	public ModelAndView getComplaintByOpenid(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession = (HttpSession)request.getSession();
		String openid = String.valueOf(httpSession.getAttribute("openid"));//得到session中的openID
		String companyCode = String.valueOf(request.getParameter("companyCode"));
		String companyName = String.valueOf(request.getParameter("companyName"));
		List<Complaint> complaintList = this.complainService.getComplaintScoreByCompanyId(companyCode,Complaint.DISPOSE_STATUS_FANKUI);
		modelAndView.addObject("complaintList", complaintList);
		modelAndView.addObject("companyName", companyName);
		modelAndView.setViewName("/complaintList");
		return modelAndView;
	}
	
	 @RequestMapping(value="/toLookImage",method = RequestMethod.GET)
     public void lookImage(HttpServletRequest request,HttpServletResponse response){
    	String pid = request.getParameter("pid");
    	Complaint complaint = this.companyInfoService.getComplaintInfoByPid(pid);
    	byte[] data= complaint.getComplainphoto();
    	response.setContentType("img/jpeg");
    	response.setCharacterEncoding("utf-8");
    	try {
    		
			OutputStream outputStream=response.getOutputStream();
			InputStream in=new ByteArrayInputStream(data);
			
			int len=0;
			byte[]buf=new byte[1024];
			while((len=in.read(buf,0,1024))!=-1){
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
     }
}
