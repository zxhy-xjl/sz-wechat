package com.sz.wechat.utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sz.wechat.entity.Menu;
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

	private static SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		
	/**
	 * 读取扫一扫所需要参数
	 * @return
	 */
	public static Map<String,Object> getScanCode(String flag,String url){
		String token = getJSSDKAccessToken();
		String ticket = "";
		if("".equals(ticket)){
			logger.error("ticket为空！");
		}
		if(!"".equals(token)){
			ticket = getJSSDKTicket(token);
		}
		String nonceStr = create_nonce_str();
		String timeStamp = create_timestamp();
		String wechat_scancode_url = "";
		if("".equals(url)){
			wechat_scancode_url="http://www.haoschoool.com/sz-wechat/scanCode.jsp";
			if(!"".equals(flag)){
				wechat_scancode_url=wechat_scancode_url+"?flag="+flag;
			}
		}else{
			wechat_scancode_url = url;
		}
		String url_1 = "jsapi_ticket=" + ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timeStamp +
                "&url=" + wechat_scancode_url;
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
		hashMap.put("url", wechat_scancode_url);
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
		String resultStr = "";//设置返回值
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
	public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
	/**
	 * 获取时间戳
	 * @return
	 */
	public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
	
	/**
	 * 得到文件上传的相对路径
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static  String getUploadPath(String fileName,String url,String newname) {
		String uploadPath = new StringBuffer("upload").append("/")
				.append(formater.format(new Date())).append("/").append(newname)
				.append(getFileExt(fileName)).toString();
		// 判断父文件夹是否存在，不存在则创建
		File dir = new File(url+"/"+uploadPath).getParentFile();
		if (!dir.exists()) {
			try {
				// 文件不存在，创建所在的文件夹
				dir.mkdirs();
			} catch (Exception e) {
				System.out.print("创建附件目录时出错");
				return "";
			}
		}
		return uploadPath;
	}
	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 打印输出略缩图
	 * @param menu
	 * @param request
	 */
	@SuppressWarnings("deprecation")
	public static void transferToFile(Menu menu,HttpServletRequest request) {
		// 获得文件路径
		String path = menu.getPath();
		String downLoadPath = request.getRealPath("/")+"/"+path;
		File file = new File(downLoadPath);
		if(!file.exists()){
			file.getParentFile().mkdirs();
			byte[] blobByte = menu.getMenuphoto();
			BufferedOutputStream bos = null;
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos);
				bos.write(blobByte);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				IOUtils.closeQuietly(bos);
				IOUtils.closeQuietly(fos);
			}
		}
		File newFile = new File(request.getRealPath("/")+"/"+path);
		try {
			FileUtils.writeByteArrayToFile(newFile, menu.getMenuphoto());
			//Thumbnails.of(file).scale(0.6f).toFile(newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
