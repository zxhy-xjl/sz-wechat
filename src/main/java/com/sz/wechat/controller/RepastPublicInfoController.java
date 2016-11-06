package com.sz.wechat.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sz.wechat.service.RepastPublicInfoService;
/**
 * 许可证信息业务逻辑层
 * @author sway
 *
 */
@Controller
public class RepastPublicInfoController {

	/**
	 * 许可证信息数据逻辑层
	 */
	@Autowired
	private RepastPublicInfoService repastPublicInfoService;
	
	
	/**
	 * 根据许可证号获取许可证信息
	 * @return
	 */
	@RequestMapping(value = "/getPublicInfo")
	public ModelAndView  getPublicInfo(HttpServletRequest request, HttpServletResponse response){
		String repastlicence = request.getParameter("repastlicence");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("RepastPublicInfo", this.repastPublicInfoService.getPublicInfo(repastlicence));
		modelAndView.addObject("chntitle", "许可证详细信息");
				modelAndView.setViewName("/licenceInfo");
		return modelAndView;
	}
	
}
