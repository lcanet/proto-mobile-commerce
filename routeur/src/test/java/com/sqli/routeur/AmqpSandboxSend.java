package com.sqli.routeur;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sqli.routeur.init.AMQPConfiguration;

public class AmqpSandboxSend {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AMQPConfiguration.class);
		context.refresh();
		
		AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
		amqpTemplate.send(new Message("12.34".getBytes(), new MessageProperties()));

		System.out.println("Sent !");
	}
}
