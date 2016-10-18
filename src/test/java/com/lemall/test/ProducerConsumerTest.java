package com.lemall.test;

import javax.jms.Destination;
import javax.jms.Message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.lilixin.registerjob.producer.ProducerService;

/**
*@Author: lilixin
*@Date: 2016年10月14日
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class ProducerConsumerTest {
	
	@Autowired
	private ProducerService producerService;
	//@Autowired
	//@Qualifier("queueDestination")
	private Destination destination;
	
	@Autowired
	private JmsTemplate jmsTemplate;

/*	@Test
	public void test(){
		for (int i = 0; i < 5; i++) {
			producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));
		}
	}*/
	
	@Test
	public void test2(){
		Message message = jmsTemplate.receive();
		System.out.println("我收到消息了"+message.toString());
	}
	
	/*@Test
	public void test1(){
		for (int i = 0; i < 5; i++) {
			producerService.sendMessage(destination,"uid&type&regtime&location&plat&opt");
		}
	}*/
}
