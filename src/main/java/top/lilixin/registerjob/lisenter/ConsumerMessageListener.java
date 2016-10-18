package top.lilixin.registerjob.lisenter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import top.lilixin.registerjob.exception.ServiceException;
import top.lilixin.registerjob.service.RegisterJobService;
import top.lilixin.registerjob.util.RemoteUtil;
import top.lilixin.registerjob.vo.RegisterVo;

/**
 * 消费队列监听器   用户注册opt
 * @Author: lilixin
 * @Date: 2016年10月14日
 */
public class ConsumerMessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerMessageListener.class);
	
	@Autowired
	private RegisterJobService registerJobService;

	/*
	 * rpc调用url
	 */
	@Value("#{prop.url}")
	private String url;
	@Value("#{prop.timeout}")
	private int timeOut;

	public void receiveMessage(Object obj) {
		LOGGER.debug("---------处理消息开始------------");
		try {
			if (obj instanceof RegisterVo) {
				RegisterVo reg = (RegisterVo) obj;
				LOGGER.debug("正在处理的消息为{}",reg==null?"**************":reg.toString());
				Map<String, Object> result = RemoteUtil.getInstance().callService(url, new HashMap<String, String>(), timeOut);
				LOGGER.debug("调用rpc返回的结果为{}",result == null?null:result.toString());
				
				registerJobService.insertRegisterData(reg);
			}
		} catch (ServiceException e) {
			LOGGER.error("rpc远程调用失败 url={}", url, e);
		} catch (Exception e) {
			LOGGER.error("receiveMessage 处理消息时出错", e);
		}
		LOGGER.debug("---------处理消息结束------------");
	}


	/*
	 * @Override public void onMessage(Message message) { if (!(message
	 * instanceof TextMessage)) { LOGGER.error("收到的消息格式不正确 msg={}", message);
	 * return; } TextMessage msg = null; try { msg = (TextMessage) message;
	 * LOGGER.info("接收到的消息为{}" , msg.getText()); String[] msgArr = getMsg(msg);
	 * 
	 * String uid = msgArr[0]; String type = msgArr[1]; String registerTime =
	 * msgArr[2]; String location = msgArr[3]; String plat = msgArr[4]; String
	 * opt = msgArr[5];
	 * 
	 * 
	 * 
	 * msg.acknowledge(); } catch (JMSException e) { e.printStackTrace(); }
	 * catch (Exception e) { LOGGER.error("消息注册消息 {} 时出现异常", msg, e); } }
	 */

	/*
	 * private String[] getMsg(TextMessage msg) throws JMSException { String
	 * text = msg.getText(); if(StringUtils.isEmpty(text)){ return null; }
	 * String[] msgArr = text.split("&"); if (msgArr != null && msgArr.length >
	 * 0) { return msgArr; } return null; }
	 */

}
