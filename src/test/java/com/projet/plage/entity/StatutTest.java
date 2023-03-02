package com.projet.plage.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StatutTest {
	
	static final String nom="confirmée";
	static final Long id = 5L;
	
	@Test
	void testerGettersSettersStatut() {
		
		Statut statut = new Statut();
		statut.setId(5L);
		statut.setNom(nom);
		
		assertEquals(id , statut.getId());
		assertEquals(nom, statut.getNom());
	}
	
	@Test
	void testerConstructeurStatutAvecParamètres() {
		Statut statut= new Statut(nom);
		
		assertEquals(nom, statut.getNom());		
		
	}

}
