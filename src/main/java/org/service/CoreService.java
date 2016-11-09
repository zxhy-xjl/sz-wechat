package org.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.message.resp.Article;
import org.message.resp.NewsMessage;
import org.message.resp.TextMessage;
import org.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author linhd
 * @date 2013-05-20
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = "回复“餐具”可以获得小知识哦！";
		try {
			// 默认返回的文本消息内容
			String respContent = "您好，这里是扫桌！回复“餐具”可以获得小知识哦！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			String userContent = requestMap.get("Content");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
         
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT) && !userContent.contains("餐具") ) {
				respContent = "您好，这里是扫桌！回复“餐具”可以获得小知识哦！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			else if(userContent.contains("餐具"))
			{
				respContent = "  如何辨别餐具是否消毒合格?\n1.看包装。上面应印有生产厂家的明确信息，如厂址、电话等；\n2.观察是否注明出厂日期或保质期；\n3.将餐具打开，先闻闻，有无刺鼻、发霉味道。";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				
			}
			
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
			{
				// 事件类型
				String eventType = requestMap.get("Event");
				NewsMessage newsMessage = new NewsMessage();  
				newsMessage.setToUserName(fromUserName);  
			    newsMessage.setFromUserName(toUserName);  
	            newsMessage.setCreateTime(new Date().getTime());  
				newsMessage.setMsgType("news");  
				newsMessage.setFuncFlag(0);  
				List<Article> articleList = new ArrayList<Article>();  
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
					 Article article = new Article();  
					 article.setTitle("扫桌使用手册");  
					 article.setDescription("民以食为天，食品安全一直是老百姓最关心的话题之一。");  
					 article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/m9NmvaEsfOmdFV2XJZYclQ0DcxdJVPav70r15PjN00hAESaSlXPRSLtqIa4kj2F32lgEym5QnJvPAonM1yHSibA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");  
					 article.setUrl("http://mp.weixin.qq.com/s?__biz=MzI3NzQ2MjEzOA==&mid=2247483680&idx=1&sn=7ccec62d0f175b0b42fef7b1974a8662&chksm=eb64ac35dc13252384a0587a01d069d5c044138c78d0190630977bc691969a0ddb5af9dd83cd&scene=4#wechat_redirect");  
					 articleList.add(article);  
					 Article article2 = new Article();  
					 article2.setTitle("为什么要扫桌？");  
					 article2.setDescription("民以食为天，食品安全一直是老百姓最关心的话题之一。");  
					 article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/m9NmvaEsfOlGB1mhFeiczG9wnwH4PrSPmrqic3aFHlUfWra7bEsDGtgyibiataOVFvpp7hEuXQ5DumCk7AibibyEibxJQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");  
					 article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzI3NzQ2MjEzOA==&mid=2247483680&idx=2&sn=9ffa2fbeb4c80a28165f69060c1df937&chksm=eb64ac35dc1325235a7e073b561f93cf8a288158e735f8616c885b05412177f56189bcd5a35f&scene=4#wechat_redirect");  
					 articleList.add(article2);  
					 Article article3 = new Article();  
					 article3.setTitle("什么是“餐饮脸谱”？");  
					 article3.setDescription("民以食为天，食品安全一直是老百姓最关心的话题之一。");  
					 article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/m9NmvaEsfOmCbnPwlfCJs3MibuzjQ65jCY0F6FA8jcqngibsg1gAGKRu7SIeS60mwumhkxgj8ibTF3kX1MBTMUsxA/640?wx_fmt=jpeg&tp=webp&wxfrom=5");  
					 article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzI3NzQ2MjEzOA==&mid=2247483680&idx=3&sn=249b8b3788cc32487df17eeffca539e9&chksm=eb64ac35dc13252308f67e2fdb517cda7970eeace9247580f283b3eae892feea2c98d3876a2a&scene=4#wechat_redirect");  
					 articleList.add(article3);  
					 Article article4 = new Article();  
					 article4.setTitle("小常识（一）消毒餐具的鉴别");  
					 article4.setDescription("民以食为天，食品安全一直是老百姓最关心的话题之一。");  
					 article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/m9NmvaEsfOmCbnPwlfCJs3MibuzjQ65jCyP0OOAFOmZhmEpcOxibGYSHZOslPHZrHcx8iapWSicareanovkiazMIpwQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");  
					 article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzI3NzQ2MjEzOA==&mid=2247483680&idx=4&sn=ccdae725649fb912d118f45d8244cd34&chksm=eb64ac35dc132523ee7de53178667f9986fed947f6d25e03a8c90c1975990ecf320261aef8b2&scene=4#wechat_redirect");  
					 articleList.add(article4);  
					 // 设置图文消息个数  
					 newsMessage.setArticleCount(articleList.size());  
					 // 设置图文消息包含的图文集合  
					 newsMessage.setArticles(articleList);  
					 // 将图文消息对象转换成xml字符串  
					 respMessage = MessageUtil.newsMessageToXml(newsMessage);  		
					
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}		
			}
	/*		// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				NewsMessage newsMessage = new NewsMessage();  
				newsMessage.setToUserName(fromUserName);  
			    newsMessage.setFromUserName(toUserName);  
	            newsMessage.setCreateTime(new Date().getTime());  
				newsMessage.setMsgType("news");  
				newsMessage.setFuncFlag(0);  
				List<Article> articleList = new ArrayList<Article>();  

				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
					 Article article = new Article();  
					 article.setTitle("我是一条单图文消息");  
					 article.setDescription("我是描述信息，哈哈哈哈哈哈哈。。。");  
					 article.setPicUrl("http://www.iteye.com/upload/logo/user/603624/2dc5ec35-073c-35e7-9b88-274d6b39d560.jpg");  
					 article.setUrl("http://tuposky.iteye.com");  
					 articleList.add(article);  
					 // 设置图文消息个数  
					 newsMessage.setArticleCount(articleList.size());  
					 // 设置图文消息包含的图文集合  
					 newsMessage.setArticles(articleList);  
					 // 将图文消息对象转换成xml字符串  
					 respMessage = MessageUtil.newsMessageToXml(newsMessage);  

					
					
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
			
			}*/

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}