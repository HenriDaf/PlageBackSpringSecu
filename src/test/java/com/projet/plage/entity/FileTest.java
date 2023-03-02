package com.projet.plage.entity;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FileTest {
	
	static final Long id = 5L;
	static final double prix= 20;
	static final double prixFaux = 10;
	static final byte numero = 4;	
	static final List<Parasol> listeParasol = new ArrayList<>();
	
	@Test
	void testerGettersSettersFile() {
		
		File file = new File();
		file.setId(5L);		
		file.setNumero(numero);
		file.setPrixJournalier(prix);
		Parasol parasol = new Parasol((byte)9, file);
		listeParasol.add(parasol);
		file.setListeParasol(listeParasol);
		
		assertEquals(id , file.getId());
		assertEquals(prix, file.getPrixJournalier());
		assertEquals(numero, file.getNumero());
		assertNotEquals(prixFaux, file.getPrixJournalier());
		assertNotNull(listeParasol);
		
	}
	
	@Test
	void testerConstructeurFileAvecParamètres() {
		
		double prix2= 25;
		byte numero2 = 8;	
		
		File file = new File();
		file.setId(5L);		
		file.setNumero(numero2);
		file.setPrixJournalier(prix);
		Parasol parasol = new Parasol((byte)9, file);
		listeParasol.add(parasol);
		
		File file2 = new File((byte)8, 25, listeParasol);		
		
		assertEquals(numero2, file2.getNumero());		
		assertEquals(prix2, file2.getPrixJournalier());
		assertNotNull(listeParasol);
	}
}
