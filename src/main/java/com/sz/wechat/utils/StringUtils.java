package com.sz.wechat.utils;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author sway
 * 说明：继承org.apache.commons.lang.StringUtils并增加一些缺失的方法
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
     * 	<p>功能描述：获取客户端IP</p>
     * 	<p>@param request 请求对象</p>
     * 	<p>@return 客户端IP</p>
     */
    public static String getIpAddr(HttpServletRequest request){  
        String ip = request.getHeader("x-forwarded-for");  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }
    /**
     *	<p>功能描述：将NULL去掉变成""输出</p> 
     */
    public static Object parseNulltoStr(Object object){
    	return object==null?"":object;
    }
    
	/**
	 * 功能描述：返回指定字节长度的字符串
	 * @param str
	 *            String 字符串
	 * @param statr
	 *            int 开始位置           
	 * @param len
	 *            int 指定长度
	 * @return String 返回的字符串
	 */
	
	public static String subStr(String str,int statr,int len){
		if(isBlank(str)||len==0) return null;
		else if(str.length()<=statr){
			return str;
		}
		else if(str.length()<(statr+len)){
			return str.substring(statr,str.length());
		}
		return str.substring(statr,statr+len)+"...";
	}
	
	/**
	 * 功能描述：返回指定字节长度的字符串
	 * @param str
	 *            String 字符串
	 * @param len
	 *            int 指定长度
	 * @return String 返回的字符串
	 */
	
	public static String subStr(String str,int len){
		if(isBlank(str)||len==0) return null;
		if(str.length()<=len){
			return str;
		}
		return str.substring(0,len)+"...";
	}
	
	/**
	 *	去除字符串为null 
	 */
	public static String clearNull(String str){
		return isNotBlank(str)?str:" ";
	}
	
	/**
     *	将null替换成""; 
     */
    public static String parseNullToStr(Object value){
    	if(null==value){
    		return "";
    	}else{
    		return value.toString();
    	}
    }
}
