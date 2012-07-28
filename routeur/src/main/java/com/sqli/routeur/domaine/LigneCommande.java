package com.sqli.routeur.domaine;

import lombok.Data;

@Data
public class LigneCommande {

	private Produit produit;
	private int quantite;
}
