package top.lilixin.registerjob.converter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import top.lilixin.registerjob.lisenter.ConsumerMessageListener;
import top.lilixin.registerjob.vo.RegisterVo;

/** msg转换器   通过这个converter把收到的消息转换成自己需要的包装类对象，返回给MessageListener
*@Author: lilixin
*@Date: 2016年10月17日
*/
public class MyMessageConverter implements MessageConverter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerMessageListener.class);

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 把消息包装成RegisterVo
	 */
	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		if (message instanceof TextMessage) {
			TextMessage textMsg = (TextMessage) message;
			String msg = textMsg.getText();
			if (msg == null) {
				return null;
			}
			String[] msgArr = msg.split("&");
			if (msgArr != null && msgArr.length == 6) {
				RegisterVo regVo = new RegisterVo();
				regVo.setUid(msgArr[0]);
				regVo.setType(msgArr[1]);
				regVo.setRegtime(msgArr[2]);
				regVo.setLocation(msgArr[3]);
				regVo.setPlat(msgArr[4]);
				regVo.setOpt(msgArr[5]);
				return regVo;
			}
			return null;
		}
		return null;
	}

}
