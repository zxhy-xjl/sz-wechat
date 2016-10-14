package com.sz.wechat.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sz.wechat.entity.Menu;
import com.sz.wechat.service.MenuService;

/***
 * 菜单控制器
 * @author sway
 *
 */
@Controller
public class MenuController {
	
	
	/**
	 * 菜单数据逻辑层
	 */
	@Autowired
	private MenuService menuService;
	

	/**
	 * 跳转至点菜页面
	 * @return
	 */
	@RequestMapping(value = "/toTakingOrder")
	public ModelAndView toTakingOrder(){
		ModelAndView modelAndView = new ModelAndView();
		List<Menu> menuList = this.menuService.getMenu();
		List<Menu> menuTypeList = new ArrayList<>();
		Menu menu = new Menu();
		menu.setMenutype("0");
		menuTypeList.add(menu);
		menu = new Menu();
		menu.setMenutype("1");
		menuTypeList.add(menu);
		modelAndView.addObject("menuTypeList", menuTypeList);
		modelAndView.addObject("menuList",menuList);
		modelAndView.setViewName("/takingorders");
		return modelAndView;
	}
}
