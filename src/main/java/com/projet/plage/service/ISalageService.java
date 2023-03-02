package com.projet.plage.service;

import java.util.List;

import com.projet.plage.entity.Salage;

public interface ISalageService {

	
	Salage ajouterSalage(Salage salage);
	
	Boolean supprimerSalage(Long id);
	
	List<Salage> recupererListe();
	
	
}
