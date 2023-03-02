package com.projet.plage.entity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

 class LocataireTest {
	
	
	static final String nom = "Doe";
	static final String prenom = "John";
	static final String email = "john@orsys.fr";
	static final String motdepasse = "123456789";
	static final Pays PAYS = new Pays("FRA","France");
	static final LienDeParente LIEN_DE_PARENTE = new LienDeParente("frère/soeur",0.5f);

	@Test
	void testerGettersSettersLocataire() {
		Locataire locataire= new Locataire();
		
		
		locataire.setEmail(email);
		locataire.setPassword(motdepasse);
		locataire.setNom(nom);
		locataire.setPrenom(prenom);
		locataire.setPays(PAYS);
		locataire.setLienDeParente(LIEN_DE_PARENTE);
		
		assertEquals(email, locataire.getEmail());
		assertEquals(motdepasse, locataire.getPassword());
		assertEquals(nom, locataire.getNom());
		assertEquals(prenom, locataire.getPrenom());
		assertEquals(PAYS, locataire.getPays());
		assertEquals(LIEN_DE_PARENTE, locataire.getLienDeParente());
		
		
	}
	
	
	
	
}
