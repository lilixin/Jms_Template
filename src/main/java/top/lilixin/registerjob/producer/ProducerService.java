package top.lilixin.registerjob.producer;

import javax.jms.Destination;

/**@deprecated
*@Author: lilixin
*@Date: 2016年10月14日
*/
public interface ProducerService {

	public void sendMessage(Destination destination, final String message) ;
}
