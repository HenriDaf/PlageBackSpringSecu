package com.projet.plage.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HexFormat;

import org.junit.jupiter.api.Test;



public class SalageTest {

	static final Concessionnaire conces = new Concessionnaire();
	static final Long id=5L;
	static final byte[] sel = HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d");
	
	@Test
	void testerGettersSettersSalage() {
		
		Salage salage = new Salage();
		salage.setId(id);
		salage.setUtilisateur(conces);
		salage.setSel(sel);
		
		assertEquals(id, salage.getId());
		assertEquals(conces, salage.getUtilisateur());
		assertEquals(sel, salage.getSel());
		
	}
	
	
	@Test
	void testerConstructeurSalageAvecParamètres() {
		Salage salage= new Salage(sel, conces);
		assertEquals(conces, salage.getUtilisateur());
		assertEquals(sel, salage.getSel());
	}
	
}
