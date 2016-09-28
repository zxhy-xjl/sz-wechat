package com.sz.wechat.utils;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;

/**
 * 扫一扫工具类
 * @author sway
 *
 */
public class ScanCodeUtils {
	
	/**
	 * 日志记录
	 */
	private static Logger logger = LoggerFactory.getLogger(ScanCodeUtils.class);
    /**
	 * wechatJSSDK的AccessToken请求URL地址
	 */
	private final static String WECHAT_JSSDK_ACCETOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx4203dd1ae2c80664&secret=68e330a8bc86b7ddc369e0f3da4a893e";
	/**
	 * wechatJSSDK的ticket请求URL地址
	 */
	private final static String WECHAT_JSSDK_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?";

		
		
	/**
	 * 读取
	 * @return
	 */
	public static Map<String,Object> getScanCode(){
		String token = getJSSDKAccessToken();
		String ticket = "";
		if("".equals(ticket)){
			logger.error("ticket为空！");
		}
		if(!"".equals(token)){
			ticket = getJSSDKTicket(token);
			System.out.println("ticket:"+ticket);
		}
		String url = "http://www.haoschoool.com/sz-wechat/scanCode.jsp";
		String nonceStr = create_nonce_str();
		String timeStamp = create_timestamp();
		String url_1 = "jsapi_ticket=" + ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timeStamp +
                "&url=" + url;
		System.out.println(url_1);
		String signature = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(url_1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("url", url);
		hashMap.put("jsapi_ticket",ticket);
		hashMap.put("nonceStr",nonceStr);
		hashMap.put("timestamp",timeStamp);
		hashMap.put("signature",signature);
		return hashMap;
	}
	/**
	 * 获取微信JSSDK的access_token
	 * @author sway
	 * @return access_token
	 */
	private static  String getJSSDKAccessToken(){
		String resultStr = "";//设置返回值
		JSONObject json = HttpRequestUtils.httpGet(WECHAT_JSSDK_ACCETOKEN_URL);
		if(null != json){
			resultStr = json.getString("access_token");
		}
		return resultStr;
	}
	/**
	 * 获取微信JSSD的ticket
	 * @param access_token
	 * @author sway
	 * @return ticket
	 */
	private static String getJSSDKTicket(String access_token){
		String resultStr = "";
		String url = WECHAT_JSSDK_TICKET_URL +"access_token="+access_token+"&type=jsapi";
		JSONObject json = HttpRequestUtils.httpGet(url);
		if(null != json){
			resultStr = json.getString("ticket");
		}
		return resultStr;
	}
	
	/**
	 * 格式化
	 * @param hash
	 * @return
	 */
	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash){
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
	/**
	 * 获取随机ID
	 * @return
	 */
	private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
	/**
	 * 获取时间戳
	 * @return
	 */
	private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
