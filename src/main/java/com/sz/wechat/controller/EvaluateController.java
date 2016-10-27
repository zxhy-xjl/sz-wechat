package com.sz.wechat.controller;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sz.wechat.entity.Evaluate;
import com.sz.wechat.service.EvaluateService;
/**
 * 评分数据业务控制器
 * @author sway
 *
 */
@Controller
public class EvaluateController {

	/**
	 * 评分数据逻辑层
	 */
	@Autowired
	private EvaluateService evaluateService;
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/doInsertEvaluate")
	public void doInsertEvaluate(HttpServletRequest request, HttpServletResponse response){
		String companyCode = request.getParameter("companyCode");
		String evaluat = request.getParameter("evaluate");
		HttpSession httpSession =(HttpSession)request.getSession();
		String openid = String.valueOf(httpSession.getAttribute("openid"));
		Evaluate evaluate = new Evaluate();
		evaluate.setPid(UUID.randomUUID().toString());
		evaluate.setOpenid(openid);
		evaluate.setCompanycode(companyCode);
		evaluate.setEvaluate(evaluat);
		this.evaluateService.doInsertEvaluate(evaluate);
	}
}
