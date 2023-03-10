package com.projet.plage.service;

import java.time.LocalDateTime;
import java.util.List;

import com.projet.plage.dto.LocationDto;
import com.projet.plage.entity.Location;


public interface ILocationService {
	
	List <Location> recupererLesLocations();			
	
	Location ajouterLocation(LocationDto locationDto);
	 
	 Location modifierLocation(LocationDto locationDto);
	 
	 Location modifierLocation(Location location);
	 
	 Location enregistrerLocation(Location location);	 
	
	 List<Location> trouverLocationsEntreDeuxDates(LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin);
	 	 
	 List<Location> trouverLocationParPrixDesc();
	 
	 List<Location> trouverLocationsParIdLocataire(Long id);
	 
	 List<Location>trouverLocationsParEmailLocataire(String email);
	 
	 Location trouverLocationParId(Long id);
	 
	 List<Location>trouverLocationsParStatut(Long id);
	 
	 Boolean supprimerLocation(Long id);
}
