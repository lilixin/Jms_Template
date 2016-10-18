package top.lilixin.registerjob.producer;  
   
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;  
   
/**@deprecated
*@Author: lilixin
*@Date: 2016年10月14日
*/
@Component  
public class ProducerServiceImpl implements ProducerService {  
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);
   
    private JmsTemplate jmsTemplate;  
      
    public void sendMessage(Destination destination, final String message) {  
       	LOGGER.info("生产者发了一个消息：{}" , message);  
        jmsTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage(message);  
            }  
        });  
    }   
  
    public JmsTemplate getJmsTemplate() {  
        return jmsTemplate;  
    }   
  
    @Resource  
    public void setJmsTemplate(JmsTemplate jmsTemplate) {  
        this.jmsTemplate = jmsTemplate;  
    }  
   
}  