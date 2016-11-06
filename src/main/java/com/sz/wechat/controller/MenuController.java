package com.sz.wechat.controller;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.basic.DictCommon;
import com.sz.wechat.entity.CodeDict;
import com.sz.wechat.entity.Consumerec;
import com.sz.wechat.entity.Menu;
import com.sz.wechat.service.ConsumerecService;
import com.sz.wechat.service.MenuService;
import com.sz.wechat.utils.ScanCodeUtils;

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
	 * 订单数据逻辑层
	 */
	@Autowired
	private ConsumerecService consumerecService;
	

	/**
	 * 跳转至点菜页面
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/toTakingOrder")
	public ModelAndView toTakingOrder(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		String companyCode = request.getParameter("companyCode");
		List<Menu> menuList = this.menuService.getMenu(companyCode);
		if(null!= menuList && menuList.size()>0){
			for (Menu menu : menuList) {
				ScanCodeUtils.transferToFile(menu,request.getRealPath("/")+"/"+menu.getPath());
			}
		}
		List<CodeDict> dictList = DictCommon.getDictListEn("MENUTYPE");
		modelAndView.addObject("dictList", dictList);
		modelAndView.addObject("menuList",menuList);
		modelAndView.addObject("chntitle","开始点菜");
		modelAndView.addObject("companyCode",request.getParameter("companyCode"));
		httpSession.setAttribute("companyCode", request.getParameter("companyCode"));
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
			HttpSession httpSession =(HttpSession)request.getSession();
			String openid = String.valueOf(httpSession.getAttribute("openid"));
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd");
			Date date=new Date();
			String oddTime=dateFormater.format(date);
			for(String menu:menuArr){
				menuArr_0 = menu.split(":");
				consumerec = new Consumerec();
				consumerec.setPid(UUID.randomUUID().toString());
				consumerec.setMenuid(menuArr_0[0]);
				consumerec.setBuynum(menuArr_0[1]);
				consumerec.setCompanycode(companyCode);
				consumerec.setOddnumber(oddNumber);
				consumerec.setOpenid(openid);
				consumerec.setOddTime(oddTime);
				list.add(consumerec);
			}
			this.consumerecService.batchInsertConsumerec(list);
			modelAndView.addObject("oddNumber", oddNumber);
			modelAndView.addObject("chntitle", "点菜成功");
			modelAndView.addObject("companyCode",companyCode);
			modelAndView.setViewName("/takingordersucceed");
		}
	return modelAndView;	
	}
	
	/**
	 * 跳转至查看下单页面
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/toMenuView")
	public ModelAndView toDownMenu(HttpServletRequest request, HttpServletResponse response){
		String oddNumber = request.getParameter("oddNumber");
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> _map = null;
		List<Map<String,Object>> mapList = new ArrayList<>();
		boolean needPlayFlag=false;
		if(!"".equals(oddNumber)){
			List<Consumerec> consumerecList = consumerecService.selectConsumerecByOddNumber(oddNumber);
			int allPrice = 0;
			int buyNum = 0;
			
			if(null != consumerecList && consumerecList.size() > 0){
				Menu menu = null;
				needPlayFlag = StringUtils.isBlank(consumerecList.get(0).getPaytime());
				for (Consumerec consumerec : consumerecList) {
					_map = new HashMap<>();
					 menu = this.menuService.getMenuByMenuId(consumerec.getMenuid());
					_map.put("menuname",menu.getMenuname());
					_map.put("menunum",consumerec.getBuynum());
					_map.put("price",menu.getPrice());
					_map.put("menutype",menu.getMenutype());
					_map.put("path", menu.getPath());
					_map.put("menuphoto", menu.getMenuphoto());
					allPrice = allPrice + (Integer.parseInt(menu.getPrice())*Integer.parseInt(consumerec.getBuynum()));
					buyNum = buyNum + Integer.parseInt(consumerec.getBuynum());
					mapList.add(_map);
				}
			}
			if(null != mapList && mapList.size() > 0){
				Menu menu = null;
				for (Map<String,Object> map : mapList) {
					menu = new Menu();
					menu.setMenuphoto((byte[])map.get("menuphoto"));
					ScanCodeUtils.transferToFile(menu,request.getRealPath("/")+"/"+String.valueOf(map.get("path")));
				}
			}
			
			List<CodeDict> dictList = DictCommon.getDictListEn("MENUTYPE");
			modelAndView.addObject("dictList", dictList);
			modelAndView.addObject("menuList", mapList);
			modelAndView.addObject("chntitle", "订单详情");
			modelAndView.addObject("allPrice", allPrice);
			modelAndView.addObject("buyNum", buyNum);
			modelAndView.addObject("oddNumber", oddNumber);
			modelAndView.addObject("companyCode",request.getParameter("companyCode"));
			modelAndView.addObject("needPlayFlag",needPlayFlag);
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
		//String companyCode = (String) httpSession.getAttribute("companyCode");
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
			HttpSession httpSession =(HttpSession)request.getSession();
			String openid = String.valueOf(httpSession.getAttribute("openid"));
			List<Consumerec> consumerecList_1 = this.consumerecService.selectConsumerByDefaultadd(openid,request.getParameter("companyCode"));
			String billunit = "";
			if(null != consumerecList_1 && consumerecList_1.size()>0){
				billunit = consumerecList_1.get(0).getBillunit();
			}
			modelAndView.addObject("menuList", mapList);
			modelAndView.addObject("allPrice", allPrice);
			modelAndView.addObject("chntitle", "结账");
			modelAndView.addObject("billunit",billunit);
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
		String invoicetype = request.getParameter("invoicetype");
		Consumerec consumerec = new Consumerec();
		consumerec.setOddnumber(oddNumber);
		consumerec.setPaytype(paytype);
		consumerec.setBillunit(invoice);
		consumerec.setDefaultadd(invoicetype);
		this.consumerecService.updatePayByOddNumber(consumerec);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmm:ss");
		modelAndView.setViewName("/doPay");
		modelAndView.addObject("allprice", request.getParameter("allprice"));
		modelAndView.addObject("chntitle", "微支付");
		modelAndView.addObject("oddNumber",oddNumber);
		modelAndView.addObject("paytime",df.format(new Date()));
		return modelAndView;
	}
	
	/**
	 * 执行图片插入
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/doInsertMenu")
	public void doInsertMenu(HttpServletRequest request, HttpServletResponse response){
		MultipartFile file = null;
		MultipartHttpServletRequest multipartRequest = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		file = multipartRequest.getFile("cover");
		if (file == null) {
			System.out.println("没有附件上传");
		}
		uploadPhoto(file,file.getOriginalFilename());
	}
	
	/**
	 * 执行插入操作
	 * @param multipartFile
	 * @param filename
	 * @return
	 */
	public int uploadPhoto(MultipartFile multipartFile,String filename){
		String url=this.getClass().getResource("/").getPath();
		url=url.substring(1, url.indexOf("/WEB-INF"));
		String newName = UUID.randomUUID().toString();
		String uploadPath = ScanCodeUtils.getUploadPath(filename,url,newName);
		String filePath = url+"/"+uploadPath;
		byte[] fileBytes = null;
		try {
			fileBytes = multipartFile.getBytes();
			multipartFile.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Menu menu = new Menu();
		menu.setMenuid(newName);
		menu.setMenuname("珍菌汤包");
		menu.setMenuphoto(fileBytes);
		menu.setMenutype("1");
		menu.setPrice("10");
		menu.setFeature("介绍：珍菌汤包选用猴头菇、茶树菇、雪耳等优质珍菌用排骨烹调,长期食用可增进人体免疫力");
		menu.setPath(uploadPath);
		return this.menuService.doInsertBlob(menu);
	}
	
	
}
