package com.projet.plage.service;

import java.util.List;

import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.Locataire;
import com.projet.plage.entity.Location;

public interface ILocataireService {

	List<Locataire> recupererLocataire();

	Locataire recupererLocataireParId(Long id);

	Locataire recupererLocataireParEmail(String email);

	
	
	Locataire ajouterLocataire(Locataire locataire);

    Locataire ajouterLocataire(LocataireDto locataireDto);
	
	List <Location> recupererLesLocationsParLocataire(Long id);		

	Locataire modifierLocataire(LocataireDto locataireDto);
//	Locataire modifierLocataire(LocataireDto locataireDto);

	Boolean supprimerLocataire(String email);
	
	Boolean supprimerLocataire(Long id);
	

}
