package com.projet.plage.service;

import java.util.List;

import com.projet.plage.dto.ParasolDto;
import com.projet.plage.entity.Parasol;


public interface IParasolService {
	
	List <Parasol> recupererListeParasols();	
		 
	Parasol ajouterParasol(ParasolDto parasolDto);
	 
	 Parasol enregistrerParasol(Parasol parasol);
	 
	 Parasol modifierParasol(ParasolDto parasolDto);
	 
	 Boolean supprimerParasol(Long id);
	 
	 Parasol trouverParasolParId(Long id);
}
