package com.sqli.routeur.domaine;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
public class Commande {

	private String id;
	private String clientId;
	
	private String coupon;
	private List<LigneCommande> items;
	
}
