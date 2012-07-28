package com.sqli.routeur.lanceur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sqli.routeur.init.AMQPConfiguration;
import com.sqli.routeur.init.CamelConfigurer;


public class RouteurMain {

	private static final Logger LOG = LoggerFactory.getLogger(RouteurMain.class);
	
	public static void main(String[] args) throws Exception {
		//
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		
		LOG.info("Démarrage du routeur");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(CamelConfigurer.class, AMQPConfiguration.class);
		context.refresh();
		
		LOG.info("Routeur démarré");
		
	}
}
