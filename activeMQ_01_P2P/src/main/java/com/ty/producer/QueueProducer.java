package com.ty.producer;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
* @Description: 消息生产者
* @ClassName: QueueProducer.java
* @version: v1.0.0
* @author:唐宇 
* @date: 2018年5月21日 下午11:00:49
 */
public class QueueProducer {
	private final static Logger LOG = LogManager.getLogger(QueueProducer.class);
	public static final String URL = "tcp://120.79.167.160:61616";
	public static void main(String[] args) throws JMSException {
		//1..创建一个ActiveMQConnectionFactory
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
		//2..建立一个connection的链接
		Connection connection = activeMQConnectionFactory.createConnection();
		//3..启动
		connection.start();
		//4..建立session
		//参数一：表示是否支持事务；参数二：表示的是应答模式
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5..声明目的地
		Destination destination = new ActiveMQQueue("ty");
		//6..创建带有目的地的生产者
		MessageProducer producer = session.createProducer(destination);
		//7..发送消息
		for (int i = 0; i < 1000; i++) {
			TextMessage message = session.createTextMessage("我的第一个activeMQ："+i);
			producer.send(message);
		}
	}
}
