package com.sqli.routeur;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sqli.routeur.domaine.Commande;

public class EnvoiCommandePeriodique {

	public static void main(String[] args) {
		RestTemplate rest = new RestTemplate();
		
		int i = 1;
		while (true) {
			
			Commande cmd = new Commande();
			cmd.setClientId("LCT9392");
			cmd.setId(i++ + "");
			
			ResponseEntity<String> response = rest.postForEntity("http://localhost:9004/commandes", cmd, String.class);
			System.out.println("REPONSE : '" + response.getBody() + "'");
			
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
			}
		}
	}
}
