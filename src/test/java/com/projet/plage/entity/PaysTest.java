package com.projet.plage.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

 class PaysTest {

	static final String nom="France";
	static final String code="FRA";
	
	@Test
	void testerGettersSettersPays() {
	
		
		Pays pays= new Pays();
		
		pays.setCode(code);
		pays.setNom(nom);
		
		
		assertEquals(code,pays.getCode());
		assertEquals(nom,pays.getNom());	
		
	}
	
	
	@Test
	void testerConstructeurPaysAvecParamètres() {
		Pays pays= new Pays(code, nom);
		
		assertEquals(code,pays.getCode());
		assertEquals(nom,pays.getNom());
		
		
	}
}
