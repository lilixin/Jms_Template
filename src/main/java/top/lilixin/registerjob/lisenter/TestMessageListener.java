package top.lilixin.registerjob.lisenter;

import javax.jms.Message;
import javax.jms.MessageListener;


/** 这种是用于不使用messageConverter的情况 需要实现MessageListener
*@Author: lilixin
*@Date: 2016年10月18日
*/
public class TestMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		
		System.out.println("我收到信息啦"+message.toString());
	}

}
