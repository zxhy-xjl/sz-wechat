package com.sz.wechat.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sz.wechat.entity.CodeDict;
import com.sz.wechat.entity.Consumerec;
import com.sz.wechat.entity.Menu;
import com.sz.wechat.service.CodeDictService;
import com.sz.wechat.service.ConsumerecService;
import com.sz.wechat.service.MenuService;
import com.sz.wechat.vo.JsonVo;

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
			String oddNumber = UUID.randomUUID().toString();
			for(String menu:menuArr){
				menuArr_0 = menu.split(":");
				consumerec = new Consumerec();
				consumerec.setPid(UUID.randomUUID().toString());
				consumerec.setMenuid(menuArr_0[0]);
				consumerec.setBuynum(menuArr_0[1]);
				consumerec.setCompanycode(companyCode);
				consumerec.setOddnumber(oddNumber);
				list.add(consumerec);
			}
			this.consumerecService.batchInsertConsumerec(list);
			modelAndView.addObject("oddNumber", oddNumber);
			modelAndView.setViewName("/takingordersucceed");
		}
	return modelAndView;	
	}
	
	/**
	 * 跳转至查看下单页面
	 * @return
	 */
	@RequestMapping(value = "/toMenuView")
	public ModelAndView toDownMenu(HttpServletRequest request, HttpServletResponse response){
		String oddNumber = request.getParameter("oddNumber");
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> _map = null;
		List<Map<String,Object>> mapList = new ArrayList<>();
		if(!"".equals(oddNumber)){
			List<Consumerec> consumerecList = consumerecService.selectConsumerecByOddNumber(oddNumber);
			int allPrice = 0;
			int buyNum = 0;
			if(null != consumerecList && consumerecList.size() > 0){
				Menu menu = null;
				for (Consumerec consumerec : consumerecList) {
					_map = new HashMap<>();
					 menu = this.menuService.getMenuByMenuId(consumerec.getMenuid());
					_map.put("menuname",menu.getMenuname());
					_map.put("menunum",consumerec.getBuynum());
					_map.put("price",menu.getPrice());
					_map.put("menutype",menu.getMenutype());
					allPrice = allPrice + (Integer.parseInt(menu.getPrice())*Integer.parseInt(consumerec.getBuynum()));
					buyNum = buyNum + Integer.parseInt(consumerec.getBuynum());
					mapList.add(_map);
				}
			}
			List<CodeDict> dictList = this.codeDictService.getDictByType("MENUTYPE");
			modelAndView.addObject("dictList", dictList);
			modelAndView.addObject("menuList", mapList);
			modelAndView.addObject("allPrice", allPrice);
			modelAndView.addObject("buyNum", buyNum);
			modelAndView.addObject("oddNumber", oddNumber);
			modelAndView.setViewName("/takingorderdetails");
		}
		return modelAndView;
	}
	
	/**
	 * 跳转至结账页面 
	 * @return
	 */
	@RequestMapping(value = "/toMenuOrder")
	public ModelAndView toMenuOrder(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		String oddNumber = request.getParameter("oddNumber");
		String allPrice = request.getParameter("allPrice");
		Map<String,Object> _map = null;
		List<Map<String,Object>> mapList = new ArrayList<>();
		if(!"".equals(oddNumber)){
			List<Consumerec> consumerecList = consumerecService.selectConsumerecByOddNumber(oddNumber);
			if(null != consumerecList && consumerecList.size() > 0){
				Menu menu = null;
				for (Consumerec consumerec : consumerecList) {
					_map = new HashMap<>();
					 menu = this.menuService.getMenuByMenuId(consumerec.getMenuid());
					_map.put("menuname",menu.getMenuname());
					_map.put("menunum",consumerec.getBuynum());
					_map.put("price",menu.getPrice());
					mapList.add(_map);
				}
			}
			modelAndView.addObject("menuList", mapList);
			modelAndView.addObject("allPrice", allPrice);
			modelAndView.addObject("oddNumber", oddNumber);
			modelAndView.setViewName("/takingordersa");
		}
		return modelAndView;
	}
	
	
	/**
	 * 执行修改支付信息
	 * @param request
	 * @param responsed
	 * @return
	 */
	@RequestMapping(value = "/doPay")
	public ModelAndView doPay(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		String oddNumber = request.getParameter("oddNumber");
		String paytype = request.getParameter("paytype");
		String invoice = request.getParameter("invoice");
		Consumerec consumerec = new Consumerec();
		consumerec.setOddnumber(oddNumber);
		consumerec.setPaytype(paytype);
		consumerec.setBillunit(invoice);
		this.consumerecService.updatePayByOddNumber(consumerec);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmm:ss");
		modelAndView.setViewName("/doPay");
		modelAndView.addObject("allprice", request.getParameter("allprice"));
		modelAndView.addObject("oddNumber",oddNumber);
		modelAndView.addObject("paytime",df.format(new Date()));
		return modelAndView;
	}
	
}
