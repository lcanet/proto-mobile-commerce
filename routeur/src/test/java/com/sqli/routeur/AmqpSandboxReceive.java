package com.sqli.routeur;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sqli.routeur.init.AMQPConfiguration;

public class AmqpSandboxReceive {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AMQPConfiguration.class);
		context.refresh();
		
		RabbitTemplate amqpTemplate = context.getBean(RabbitTemplate.class);
		while (true) {
			Thread.sleep(100L);
			Message msg = amqpTemplate.receive("commandesQ");
			if (msg != null) {
				System.out.println("GOT MSG " + msg);
			}
		}
	}
}
