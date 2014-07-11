package com.pivotal.archiver;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessageChecker {
	public static void main(String [] args) throws Exception {

		// TODO setup the connection with a ConnectionFactory (see slides for examples)
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		//actory.setVirtualHost("/");
		factory.setHost("localhost");
		factory.setPort(5672);
		
		Connection connection = factory.newConnection();
		
		Channel channel = connection.createChannel();
		
		channel.basicConsume("si-syslog.message.queue", true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
				System.out.println("receiving: "+new String(body));				
			}
		});		
	}
}
