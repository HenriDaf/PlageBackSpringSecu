package com.projet.plage.entity;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class LienDeParenteTest {
	
	static final String nom="frère/soeur";
	static final float coefficient=0.5f;
	
	@Test 
	void testerGettersSettersLienDeParente() {
	
		LienDeParente lienDeParente = new LienDeParente();
		lienDeParente.setCoefficient(coefficient);
		lienDeParente.setNom(nom);
		
		
		
		//assertEquals(coefficient, lienDeParente.getCoefficient());
		
		assertEquals(nom, lienDeParente.getNom());
	
		
	
	
	}
	
	@Test
	void testerConstructeurLienDeParenteAvecParamètres() {
		
		LienDeParente lienDeParente = new LienDeParente(nom,coefficient);
		assertEquals(nom, lienDeParente.getNom());
		//assertEquals(coefficient, lienDeParente.getCoefficient());
	}
}
