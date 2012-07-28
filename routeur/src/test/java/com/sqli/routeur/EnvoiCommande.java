package com.sqli.routeur;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sqli.routeur.domaine.Commande;

public class EnvoiCommande {

	public static void main(String[] args) {
		RestTemplate rest = new RestTemplate();
		
		Commande cmd = new Commande();
		cmd.setClientId("LCT9392");
		cmd.setId("1");
		
		ResponseEntity<String> response = rest.postForEntity("http://localhost:9004/commandes", cmd, String.class);
		System.out.println("REPONSE : '" + response.getBody() + "'");
	}
}
