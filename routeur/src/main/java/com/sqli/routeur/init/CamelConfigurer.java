package com.sqli.routeur.init;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sqli.routeur.routes.CommandesRoute;

@Configuration
public class CamelConfigurer {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public CamelContext camelContext() throws Exception {
		CamelContext context = new SpringCamelContext(applicationContext);
		context.addRoutes(new CommandesRoute());
		return context;
	}
	
}
