package com.projet.plage.dao;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.plage.entity.Locataire;
import com.projet.plage.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {
	
	public Optional<Location> findById(Long id);
	

	List<Location> findAllByLocataire(Locataire locataire);

	@Query(
            """ 
            FROM location
            WHERE date_heure_debut= ?1 AND date_heure_fin= ?2
            """
    )
    List<Location> findLocationsAddedBetweenDates(LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin);
	
	@Query(
            """ 
            FROM location
            ORDER BY montantaregler_en_euros DESC
            """
    )
    List<Location> findLocationsWithPriceDesc();
	
	@Query(
            """ 
            FROM location
            WHERE id_locataire=?1
            """
    )
    List<Location> findLocationsByIdLocataire(Long id);
	
	@Query(
            """ 
            FROM location
            WHERE id_statut=?1
            """
    )
    List<Location> findLocationsByIdStatut(Long id);


}
