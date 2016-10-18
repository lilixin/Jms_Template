package top.lilixin.registerjob.controller;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import top.lilixin.registerjob.vo.RegisterVo;

/**
 * 用来向exchange发送消息   localhost:80/test/produce.json
*@Author: lilixin
*@Date: 2016年10月18日
 */
@Controller
@RequestMapping("test")
public class TestController {
	
	@Resource(name="producerJmsTemplate")
    private JmsTemplate producerJmsTemplate;
     
	
	@RequestMapping("produce")
	@ResponseBody
	public Object test(){
		 producerJmsTemplate.send(new MessageCreator() {
             @Override
             public Message createMessage(Session session) throws JMSException {
            	 TextMessage message = createMsg(session);
                 return message;
             }
         });
		return "成功";
	}
	
	 /*
     * 构造发送的测试消息内容
     */
    private TextMessage createMsg(Session session) throws JMSException{
        //只发送字符串
        //TextMessage tmsg = session.createTextMessage(new String(msg));
        TextMessage msg = session.createTextMessage();
        msg.setText("uid&type&regtime&location&plat&opt");
        return msg;
    }
	
	/*public static void main(String[] args) throws JMSException {
			String MQ_LOCATION = "amqp://10.100.151.12:5672";
	        // ConnectionFactory ：连接工厂，JMS 用它创建连接
	        ActiveMQConnectionFactory connectionFactory = null;
	            connectionFactory= new ActiveMQConnectionFactory(MQ_LOCATION);
	            PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
	        pooledConnectionFactory.setConnectionFactory(connectionFactory);
	        // 设置最大连接数
	        pooledConnectionFactory.setMaxConnections(100);
	        // 设置最小
	        pooledConnectionFactory.setMaximumActiveSessionPerConnection(500);
	        System.out.println("成功");
	        JmsTemplate jms = new JmsTemplate(pooledConnectionFactory);
	        //jms.convertAndSend(new ActiveMQQueue("lemall.sso_user_register_queue"), "tttttttttttttttttttttttttttt");
	        TextMessage message = (TextMessage)jms.receive(new ActiveMQQueue("lemall.sso_user_register_queue"));
	        System.out.println("message========="+message.getText());
	}*/
	
	
    @RequestMapping("2")
	@ResponseBody
	public Object test2(){
		RegisterVo v = new RegisterVo();
		v.setUid("成功调用rpc");
		return JSON.toJSONString(v);
	}
}
