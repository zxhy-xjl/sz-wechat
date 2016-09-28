package com.sz.wechat.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sz.wechat.entity.ScanCode;
import com.sz.wechat.utils.ScanCodeUtils;
import com.sz.wechat.vo.JsonVo;

/***
 * wechat扫一扫控制器
 * @author sway
 *
 */
@Controller
public class ScanCodeController {

	
	/**
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/scanCode/sign")
	public JsonVo  sign(){
		Map<String,Object> map = ScanCodeUtils.getScanCode();
		JsonVo  jsonVo = new JsonVo();
		ScanCode scanCode = null;
		if(null != map){
			scanCode = new ScanCode();
			scanCode.setTicket(String.valueOf(map.get("jsapi_ticket")));
			scanCode.setNonce(String.valueOf(map.get("nonceStr")));
			scanCode.setTimestamp(String.valueOf(map.get("timestamp")));
			scanCode.setSignature(String.valueOf(map.get("signature")));
		}
		jsonVo.setResult(scanCode);
		return jsonVo;
	}

/*	public static void main(String[] args) {
		Map<String,Object> map = new ScanCodeUtils().getScanCode();
		for (Map.Entry entry :map.entrySet()) {
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
		System.out.println(map);
	}*/
	
}
