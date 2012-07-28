package com.sqli.routeur.routes;

import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.commons.io.IOUtils;

import com.sqli.routeur.domaine.Commande;

public class CommandesRoute extends RouteBuilder {

	private int portNum = 9004;
	
	@Override
	public void configure() throws Exception {
		from("restlet:http://localhost:" + portNum + "/commande?restletMethods=post")
		.unmarshal().json(JsonLibrary.Jackson, Commande.class)
		.log(LoggingLevel.DEBUG, "com.sqli.Commandes", "Commande reçue : ${in.body.id}")
		.removeHeaders("*")
		.to("spring-amqp:commandesExFO3?type=fanout")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getOut().setBody("OK");
				
			}
		})
		;
		
		from("spring-amqp:commandesExFO3:commandesQ2?type=fanout").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("In Queue " + exchange.getIn());
				
			}
		});
	}
}
