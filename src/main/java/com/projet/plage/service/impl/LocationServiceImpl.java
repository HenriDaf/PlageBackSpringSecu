package com.projet.plage.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.plage.dao.LocationDao;
import com.projet.plage.dto.LocationDto;
import com.projet.plage.entity.Location;
import com.projet.plage.mapper.LocationMapper;
import com.projet.plage.service.ILocationService;

@Service
public class LocationServiceImpl implements ILocationService {
	
	@Autowired
	private LocationDao locationDao;
	private LocationMapper locationMapper;
	
	@Override
	public List<Location> recupererLesLocations() {		
		return locationDao.findAll();
	}
	@Override
	public Location ajouterLocation(LocationDto locationDto) {
		Location location = locationMapper.toEntity(locationDto);
		return enregistrerLocation(location);
	}
	@Override
	public Location modifierLocation(LocationDto locationDto) {		
		Location location = locationMapper.toEntity(locationDto);
		return enregistrerLocation(location);
	}
	
	@Override
	public Location modifierLocation(Location location) {
		
		return enregistrerLocation(location);
	}
	@Override
	public Boolean supprimerLocation(Long id) {
		if (locationDao.existsById(id)) {
			locationDao.deleteById(id);
			return true;
		}
		return false;
	}
	@Override
	public Location enregistrerLocation(Location location) {
		return locationDao.save(location);
	}
	@Override
	public List<Location> trouverLocationsEntreDeuxDates(LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin) {		
		return locationDao.findLocationsAddedBetweenDates(dateHeureDebut, dateHeureFin);
	}
	@Override
	public List<Location> trouverLocationParPrixDesc() {	
		return  locationDao.findLocationsWithPriceDesc();
	}
	@Override
	public List<Location> trouverLocationsParIdLocataire(Long id) {		
		return locationDao.findLocationsByIdLocataire(id);
	}
	@Override
	public Location trouverLocationParId(Long id) {		
		return locationDao.findById(id).orElse(null);
	}
	@Override
	/**
	 * Trouver des locations par leur statut
	 */
	public List<Location> trouverLocationsParStatut(Long id) {		
		return locationDao.findLocationsByIdStatut(id);
	}
	

}
