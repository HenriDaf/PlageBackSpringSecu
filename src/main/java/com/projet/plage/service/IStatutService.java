package com.projet.plage.service;

import java.util.List;

import com.projet.plage.dto.StatutDto;
import com.projet.plage.entity.Statut;


public interface IStatutService {
	
	List <Statut> recupererStatuts();	

	Statut recupererStatutParNom(String nom);	
	
	Statut recupererStatutParId(Long id);	
	 
	Statut ajouterStatuts(StatutDto statutDto);
	
	Statut enregistrerStatut(Statut statut);
	 
	 Statut modifierStatut(StatutDto statutDto);
	 
	 Boolean supprimerStatut(Long id);
}
