package com.projet.plage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.projet.plage.dto.ConcessionnaireDto;
import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.Concessionnaire;
import com.projet.plage.service.IConcessionnaireService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;


/**
 * 
 * @author Henri.
 * 
 * Ce controlleur nous permet de:
 * 
 * récupérer la liste des concessionnaires via la la méthode recupererListeConcessionnaire(),
 * d'authentifer un concessionnaire via la méthode  authentifierConcessionnaire(),
 * de créer un concessionnaire via la méthode ajouterConcessionnaire(). 
 * 
 * l'annotation @CrossOrigin(origins = "http://localhost:4200/") nous permet d'autoriser les requetes provenant de notre
 * front Angular.
 *
 */

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ConcessionnaireControllerRest {
	
	IConcessionnaireService iConcessionnaireService;
	
	@GetMapping("recupererListeConcessionnaire")
	@ResponseStatus(code=HttpStatus.OK)
	@Operation(description = "Affichage de la liste de concessionnaire")
	public List<ConcessionnaireDto> recupererListeConcessionnaire() {
		
	List<Concessionnaire> concessionnaires=iConcessionnaireService.recupererConcessionnaire();	
	
	List<ConcessionnaireDto> concessionnairesDtos=new ArrayList<>();
	//concessionnaires.forEach(concessionnaire->concessionnairesDtos.add(new ConcessionnaireDto(concessionnaire.getId(), concessionnaire.getNom(), concessionnaire.getPrenom(), concessionnaire.getEmail(), concessionnaire.getNumeroDeTelephone(), concessionnaire.getRole())));
	concessionnaires.forEach(concessionnaire->concessionnairesDtos.add(ConcessionnaireDto.builder()
			.nom(concessionnaire.getNom())
			.prenom(concessionnaire.getPrenom())
			.password(concessionnaire.getPassword())
			.email(concessionnaire.getEmail())
			.numeroDeTelephone(concessionnaire.getNumeroDeTelephone())
			.role(concessionnaire.getRole())
			.id(concessionnaire.getId())
			.build()));
	
	
	return concessionnairesDtos;
	
	}
	

	
	@PostMapping("creationConcessionnaire")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(description = "ajouter un Concessionnaire")
	public Concessionnaire ajouterConcessionnaire(@RequestBody ObjectNode objectNode) {
		
		String email= objectNode.get("email").asText();
		String nom= objectNode.get("nom").asText();
		String prenom= objectNode.get("prenom").asText();
		String motDePasse= objectNode.get("password").asText();
		String numeroDeTelephone= objectNode.get("numeroDeTelephone").asText();
		
		
	ConcessionnaireDto concessionnaireDto= new ConcessionnaireDto(nom, prenom, email, motDePasse, numeroDeTelephone);
		return iConcessionnaireService.ajouterConcessionnaire(concessionnaireDto);
	//	return null;
		
	}
}
