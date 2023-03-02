package com.projet.plage.service;

import java.util.List;

import com.projet.plage.dto.PaysDto;
import com.projet.plage.entity.Pays;

public interface IPaysService {
	
	List <Pays> recupererPays();
	
	Pays recupererPaysParCode(String code);

	Pays recupererPaysParNom(String nom);
	
	 //Pays ajouterPays(Pays pays);
	Pays ajouterPays(PaysDto paysDto);
	 
	 Pays modifierPays(PaysDto paysDto);
	 
	 Boolean supprimerPays(String code);
	

}
