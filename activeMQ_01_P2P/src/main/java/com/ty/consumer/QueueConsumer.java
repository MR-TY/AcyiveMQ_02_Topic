package com.ty.consumer;

import java.util.function.Consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Description: 消息消费者
 * @ClassName: QueueConsumer.java
 * @version: v1.0.0
 * @author: 唐宇
 * @date: 2018年5月21日 下午11:01:51
 */
public class QueueConsumer {
	private final static Logger LOG = LogManager.getLogger(QueueConsumer.class);
	public static final String URL = "tcp://120.79.167.160:61616";

	public static void main(String[] args) throws JMSException {
		// 1..创建一个ActiveMQConnectionFactory
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
		// 2..建立一个connection的链接
		Connection connection = activeMQConnectionFactory.createConnection();
		// 3..启动
		connection.start();
		// 4..建立session
		// 参数一：表示是否支持事务；参数二：表示的是应答模式
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5..声明目的地
		Destination destination = new ActiveMQQueue("ty");
		//6..创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		//7..接收消息
		TextMessage message = (TextMessage) consumer.receive(30000);
		//8..打印消息
		System.out.println("消费的消息是："+message.getText());
	}
}
