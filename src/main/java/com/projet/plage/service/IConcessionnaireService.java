package com.projet.plage.service;

import java.util.List;

import com.projet.plage.dto.ConcessionnaireDto;
import com.projet.plage.entity.Concessionnaire;

public interface IConcessionnaireService {
	
	
	/*
	 * Inutilise pour le moment vu qu'il n'y a qu'un seul concessionnaire
	 */
	
	List<Concessionnaire> recupererConcessionnaire();
	
	Concessionnaire recupererParId(Long id);
	
	Concessionnaire recupererParEmail(String email);
	
	
//	Concessionnaire ajouterConcessionnaire(Concessionnaire concessionnaire);

	Concessionnaire ajouterConcessionnaire(ConcessionnaireDto concessionnaireDto);

	Concessionnaire modifierConcessionnaire(Concessionnaire concessionnaire);
	//Concessionnaire ajouterConcessionnaire(ConcessionnaireDto concessionnaireDto);
	
	
	
	/*
	 * Surtout en bonus
	 */
    Boolean supprimerConcessionnaire(String email);
   
   
   
}
