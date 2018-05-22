package com.ty.consumer;

import java.util.function.Consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
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
public class QueueConsumer1 {
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
		//7..第二种消费的模式
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage message2=(TextMessage) message;		
				try {
					System.out.println("消费到的数据是:"+message2.getText());
				} catch (JMSException e) {
					System.out.println("出异常了...");
				}
			}
		});
	}
}
