package com.sqli.routeur.init;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
				"localhost");
		return connectionFactory;
	}

	@Bean
	public AmqpTemplate amqpTemplate() {
		RabbitTemplate template = new RabbitTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setMessageConverter(new JsonMessageConverter());
//		template.setExchange(commandesExchange().getName());
		return template;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	/*
    @Bean
    public FanoutExchange commandesExchange() {
        return new FanoutExchange("commandesExFO");
    }

    @Bean
    public Queue commandesQueue() {
        return new Queue("commandesQ");
    }
    
	@Bean
	public Binding marketDataBinding() {
	    return BindingBuilder.bind(
	    		commandesQueue()).to(commandesExchange());
	}
	*/
}
