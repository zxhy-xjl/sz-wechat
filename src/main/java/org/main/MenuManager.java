package org.main;

import java.io.UnsupportedEncodingException;

import org.pojo.AccessToken;
import org.pojo.Button;
import org.pojo.CommonButton;
import org.pojo.ComplexButton;
import org.pojo.Menu;
import org.pojo.ViewButton;
import org.util.WeixinUtil;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		
		//String myUrl = "http://www.haoschoool.com/sz_wechattest/Oauth2Servlet";
		String   myUrl   =   java.net.URLEncoder.encode("http://www.haoschoool.com/sz-wechat/checkScanCodeTableWare.do?company=","utf-8"); 
		System.out.println(myUrl);
		
/*		//获取所有图片素材
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=hoALATJUjYJ9skmta816xD90GY0Xre4SQm3FO3Hq7qLhU9VzVzZWa7aGs7UdMTz2VCjc-JSIWEteo9BZoq22aMLGBgLJYL7sDr0l3UikqPMDZVeAFAQMI";
		//添加图文素材
		String requestUrl2 = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=hoALATJUjYJ9skmta816xD90GY0Xre4SQm3FO3Hq7qLhU9VzVzZWa7aGs7UdMTz2VCjc-JSIWEteo9BZoq22aMLGBgLJYL7sDr0l3UikqPMDZVeAFAQMI";
		//群发图文消息
		String requestUrl3 = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=hoALATJUjYJ9skmta816xD90GY0Xre4SQm3FO3Hq7qLhU9VzVzZWa7aGs7UdMTz2VCjc-JSIWEteo9BZoq22aMLGBgLJYL7sDr0l3UikqPMDZVeAFAQMI";
		String outputStr = "{\"type\":\"image\",\"offset\":0,\"count\":10}";
		String outputStr2 = "{\"articles\": [{\"title\": \"test\",\"thumb_media_id\": \"vqwx7kVbaYu50eukSirkCBeS6qpSCoqlluavQRyFzXE\",\"author\": \"无名\",\"digest\": \"测试中\",\"show_cover_pic\": 1,\"content\": \"大家好，我是扫桌小助手\",\"content_source_url\": \"http://mp.weixin.qq.com/s?__biz=MzA4NDEzNTAxNg==&mid=2650763492&idx=1&sn=e0b41715b796a011c53cec67cba8a222&scene=4#wechat_redirect\"},]}";
		String outputStr3 = "{\"filter\":{\"is_to_all\":true},\"mpnews\":{\"media_id\":\"vqwx7kVbaYu50eukSirkCIuUdnAge8jlUKY-uFnP5Ws\"},\"msgtype\":\"mpnews\"}";
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", outputStr);
		JSONObject jsonObject2 = WeixinUtil.httpRequest(requestUrl2, "POST", outputStr2);
		JSONObject jsonObject3 = WeixinUtil.httpRequest(requestUrl3, "POST", outputStr3);
		System.out.println(jsonObject3.toString());*/
		
		//图文消息mediaid:vqwx7kVbaYu50eukSirkCIuUdnAge8jlUKY-uFnP5Ws
		// 第三方用户唯一凭证
/*     	String appId = "wx4203dd1ae2c80664";
		// 第三方用户唯一凭证密钥
		String appSecret = "68e330a8bc86b7ddc369e0f3da4a893e";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		//AccessToken at = new AccessToken();
		//at.setToken("hoALATJUjYJ9skmta816xD90GY0Xre4SQm3FO3Hq7qLhU9VzVzZWa7aGs7UdMTz2VCjc-JSIWEteo9BZoq22aMLGBgLJYL7sDr0l3UikqPMDZVeAFAQMI");
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
           
			// 判断菜单创建结果
			if (0 == result)
				System.out.println("菜单创建成功！");
				//log.info("菜单创建成功！");
			else
				System.out.println("菜单创建失败，错误码：" + result);
				//log.info("菜单创建失败，错误码：" + result);
		}*/
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
/*		CommonButton btn11 = new CommonButton();
		btn11.setName("天气预报");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("公交查询");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("周边搜索");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("历史上的今天");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("歌曲点播");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("经典游戏");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("美女电台");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("人脸识别");
		btn24.setType("click");
		btn24.setKey("24");

		CommonButton btn25 = new CommonButton();
		btn25.setName("聊天唠嗑");
		btn25.setType("click");
		btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("Q友圈");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("电影排行榜");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("幽默笑话");
		btn33.setType("click");
		btn33.setKey("33");*/

		/*ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("扫一扫");
		//mainBtn1.setSub_button(new CommonButton[] { });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("足迹");
		//mainBtn2.setSub_button(new CommonButton[] {});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("我的");*/
		
		//mainBtn3.setSub_button(new CommonButton[] {  });
		

	
		//complexBtn.setSub_button(new CommonButton[] { });
		
		ViewButton mainBtn1 = new ViewButton();
		mainBtn1.setName("扫桌");
		mainBtn1.setUrl("http://www.haoschoool.com/sz-wechat/scanCode.jsp?flag=1");
		mainBtn1.setType("view");
		ViewButton viewBtn = new ViewButton();
		viewBtn.setName("扫餐具");
		viewBtn.setUrl("http://www.haoschoool.com/sz-wechat/scanCode.jsp?flag=2");
		viewBtn.setType("view");
		
		ComplexButton complexBtn = new ComplexButton();
		complexBtn.setName("扫一扫");
		complexBtn.setSub_button(new ViewButton[] {mainBtn1,viewBtn });

		ViewButton mainBtn2 = new ViewButton();
		mainBtn2.setName("足迹");
		mainBtn2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4203dd1ae2c80664&redirect_uri=http%3A%2F%2Fwww.haoschoool.com%2Fsz-wechat%2FtoFootprint.do&response_type=code&scope=snsapi_userinfo&state=222#wechat_redirect");
		mainBtn2.setType("view");
		//mainBtn2.setSub_button(new CommonButton[] {});

		ViewButton viewBtn3 = new ViewButton();
		viewBtn3.setName("我的");
		viewBtn3.setType("view");
		viewBtn3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4203dd1ae2c80664&redirect_uri=http%3A%2F%2Fwww.haoschoool.com%2Fsz-wechat%2Fjsp%2Fuserinfo.jsp&response_type=code&scope=snsapi_userinfo&state=111#wechat_redirect");
		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { complexBtn, mainBtn2, viewBtn3 });

		return menu;
	}
}