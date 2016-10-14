package com.sz.wechat.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sz.wechat.entity.CodeDict;
import com.sz.wechat.entity.Consumerec;
import com.sz.wechat.entity.Menu;
import com.sz.wechat.service.CodeDictService;
import com.sz.wechat.service.ConsumerecService;
import com.sz.wechat.service.MenuService;

import sun.misc.UUDecoder;

/***
 * 菜单控制器
 * @author sway
 *
 */
@Controller
public class MenuController  {
	
	
	/**
	 * 菜单数据逻辑层
	 */
	@Autowired
	private MenuService menuService;
	/**
	 * 字典数据逻辑层
	 */
	@Autowired
	private CodeDictService codeDictService;
	/**
	 * 订单数据逻辑层
	 */
	@Autowired
	private ConsumerecService consumerecService;
	

	/**
	 * 跳转至点菜页面
	 * @return
	 */
	@RequestMapping(value = "/toTakingOrder")
	public ModelAndView toTakingOrder(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		List<Menu> menuList = this.menuService.getMenu();
		List<CodeDict> dictList = this.codeDictService.getDictByType("MENUTYPE");
		modelAndView.addObject("dictList", dictList);
		modelAndView.addObject("menuList",menuList);
		modelAndView.addObject("companyCode",request.getParameter("companyCode"));
		modelAndView.setViewName("/takingorders");
		return modelAndView;
	}
	
	/**
	 * 执行下单新增
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doAddMenu")
	public ModelAndView doAddMenu(HttpServletRequest request, HttpServletResponse response){
		String menus = request.getParameter("menuJson");
		String companyCode = request.getParameter("companyCode");
		ModelAndView modelAndView = new ModelAndView();
		if(!"".equals(menus)){
			String [] menuArr = menus.split(",");
			String [] menuArr_0 = null;
			Consumerec consumerec = null;
			List<Consumerec> list = new ArrayList<>();
			for(String menu:menuArr){
				menuArr_0 = menu.split(":");
				consumerec = new Consumerec();
				consumerec.setPid(UUID.randomUUID().toString());
				consumerec.setMenuid(menuArr_0[0]);
				consumerec.setBuynum(menuArr_0[1]);
				consumerec.setCompanycode(companyCode);
				list.add(consumerec);
			}
			this.consumerecService.batchInsertConsumerec(list);
		}
		modelAndView.setViewName("/takingordersucceed");
	return modelAndView;	
	}
}
