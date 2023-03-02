package com.projet.plage.entity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ConcessionnaireTest {

	static final String nom = "Doe";
	static final String prenom = "John";
	static final String email = "john@orsys.fr";
	static final String motdepasse = "123456789";
	static final String numeroDeTelephone = "+3912345678";

	@Test

	void testerGettersSettersConcessionnaire() {
		Concessionnaire concessionnaire = new Concessionnaire();

		concessionnaire.setEmail(email);
		concessionnaire.setPassword(motdepasse);
		concessionnaire.setNom(nom);
		concessionnaire.setPrenom(prenom);
		concessionnaire.setNumeroDeTelephone(numeroDeTelephone);

		assertEquals(numeroDeTelephone, concessionnaire.getNumeroDeTelephone());
		assertEquals(email, concessionnaire.getEmail());
		assertEquals(nom, concessionnaire.getNom());
		assertEquals(prenom, concessionnaire.getPrenom());
		assertEquals(motdepasse, concessionnaire.getPassword());

	}
	
	
	
	@Test
	void testerConstructeurAvecParametres() {
		Salage salage= new Salage();
		Concessionnaire concessionnaire = new Concessionnaire(nom,prenom,email,motdepasse,numeroDeTelephone,salage);
		
		
		assertEquals(numeroDeTelephone, concessionnaire.getNumeroDeTelephone());
		assertEquals(email, concessionnaire.getEmail());
		assertEquals(nom, concessionnaire.getNom());
		assertEquals(prenom, concessionnaire.getPrenom());
		assertEquals(motdepasse, concessionnaire.getPassword());
	}
}
