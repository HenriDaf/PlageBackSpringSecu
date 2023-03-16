package com.projet.plage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.projet.plage.dto.LocationDto;
import com.projet.plage.dto.ParasolDto;
import com.projet.plage.entity.Concessionnaire;
import com.projet.plage.entity.File;
import com.projet.plage.entity.Location;
import com.projet.plage.entity.Parasol;
import com.projet.plage.entity.Statut;
import com.projet.plage.service.IFileService;
import com.projet.plage.service.ILocataireService;
import com.projet.plage.service.ILocationService;
import com.projet.plage.service.IStatutService;
import com.projet.plage.service.impl.ConcessionnaireServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
@CrossOrigin(origins = "http://localhost:4200/")
public class LocationControllerRest {

	private final ILocationService locationService;

	private final ConcessionnaireServiceImpl concessionnaireService;

	private final ILocataireService locataireService;

	private final IFileService fileService;

	private final IStatutService statutService;

	/**
	 * Méthode pour ajouter des locations
	 * 
	 * @param location
	 * @return
	 */
	@PostMapping("/locations/create")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Location> postLocation(@RequestBody LocationDto location) {

		// récupération de ld du concessionnaire
		Concessionnaire concessionaire = concessionnaireService.recupererConcessionnaire().get(0);

		// récupération id du statut
		Statut statut = statutService.recupererStatutParNom(location.getStatutDto().getNom());

		// liste des parasols
		List<Parasol> parasols = new ArrayList<>();
		File file = null;

		for (ParasolDto parasolDto : location.getListeParasolDto()) {
			Parasol parasol = new Parasol();
			parasol.setId(parasolDto.getId());
			parasol.setNumEmplacement(parasolDto.getNumEmplacement());
			file = fileService.trouverFileParId(parasolDto.getFileDto().getId());
			parasol.setFile(file);
			parasols.add(parasol);
		}

		Location newLocation = new Location(location.getDateHeureDebut(), location.getDateHeureFin(),
				location.getMontantAReglerEnEuros(), location.getRemarques(),
				locataireService.recupererLocataireParId(location.getIdLocataire()), parasols, statut, concessionaire);

		locationService.enregistrerLocation(newLocation);

		return new ResponseEntity<>(newLocation, HttpStatus.CREATED);

	}

	/**
	 * Récupérer la liste des locations par locataire
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/locations/locataire/{id}")
	public ResponseEntity<List<Location>> getLocationByLocataire(@PathVariable String id) {
		List<Location> locations = locationService.trouverLocationsParIdLocataire(Long.parseLong(id));

		if (locations.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(locations);
	}

	@GetMapping("/locations/location/{id}")
	public ResponseEntity<Location> getLocationById(@PathVariable String id) {
		Location location = locationService.trouverLocationParId(Long.parseLong(id));

		if (location == null) {
			return ResponseEntity.badRequest().body(null);
		}

		return ResponseEntity.ok(location);
	}
	
	@PreAuthorize("hasAuthority('LOCATAIRE')")
	@GetMapping("/locations/locataireMail/{email}")
	public ResponseEntity<List<Location>> getLocationByLocataireMail(@PathVariable String email){
		List<Location> locations=locationService.trouverLocationsParEmailLocataire(email);
		
		if(locations.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}
	
		return ResponseEntity.ok(locations);
		}

	@PreAuthorize("hasAuthority('CONCESSIONNAIRE')")
	@GetMapping("/locations/liste-location")
	public ResponseEntity<List<Location>> getAllLocation() {

		List<Location> locations = locationService.recupererLesLocations();
System.out.println(locations);
		if (locations.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
			
			//TODO modifier ce comportement pour pouvoir afficher un message Aucune location plutot que l'erreur 400;
		}

		return ResponseEntity.ok(locations);
	}

	@PreAuthorize("hasAuthority('CONCESSIONNAIRE')")
	@GetMapping("/locations/liste-statut/traitement")
	public ResponseEntity<List<Location>> getLocationByStatutATraiter() {
		String nom = "à traiter";
		Statut statut = statutService.recupererStatutParNom(nom);

		List<Location> locations = locationService.trouverLocationsParStatut(statut.getId());

		if (locations.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}

		return ResponseEntity.ok(locations);
	}

	
	@PatchMapping("/locations/location/{id}")
	public ResponseEntity<Location> getModifierStatutLocation(@PathVariable String id,
			@RequestBody ObjectNode objectNode) {
		Location location = locationService.trouverLocationParId(Long.parseLong(id));

		if (location == null) {
			return ResponseEntity.noContent().build();
		}

		// récupérer le statut modifier par l'id
		Long idStatut = Long.parseLong(objectNode.get("statut").asText());
		Statut statutTrouver = statutService.recupererStatutParId(idStatut);
		location.setStatut(statutTrouver);

		// modifier la location
		locationService.enregistrerLocation(location);
		return ResponseEntity.ok(location);

	}
	@PreAuthorize("hasAuthority('CONCESSIONNAIRE')")
	@PutMapping("/changerStatutLocation")
	public ResponseEntity<Location> modifierStatutLocation(@RequestBody ObjectNode objectNode) throws Exception {

		Long id = objectNode.get("id").asLong();
		Long idStatut = objectNode.get("statut").asLong();


		Location location = locationService.trouverLocationParId(id);
		Statut statut = statutService.recupererStatutParId(idStatut);
		
		if (location != null && statut != null) {


			location.setStatut(statut);

			locationService.modifierLocation(location);

			return ResponseEntity.ok(location);
		}else {
			throw new Exception("Erreur lors de la validation du changement de statut, veuillez réessayer ultérieurement");
			
		}

	}

}
