package com.projet.plage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.projet.plage.dto.ConcessionnaireDto;
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

@RestController("/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ConcessionnaireControllerRest {
	
	IConcessionnaireService iConcessionnaireService;
	
	@GetMapping("recupererListeConcessionnaire")
	@ResponseStatus(code=HttpStatus.OK)
	@Operation(description = "Affichage de la liste de concessionnaire")
	public List<Concessionnaire> recupererListeConcessionnaire() {
		return iConcessionnaireService.recupererConcessionnaire();	
	
	}
	/**
	 * 
	 * @param objectNode
	 * @return
	 * 
	 * 
	 * Méthode prenant en paramètre ObjectNode.
	 * 
	 * Cet object contiendra les données provenant du formulaire d'authentification coté front.
	 * 
	 * On récupèrera via la méthode get(), les champs email et password respectivement.
	 * A l'aide de la méthode authentifierConcessionnaireParEmailMotDePasse() de l'interface IConcessionnaireService, on récupère
	 * le concessionnaire conrrespondant.
	 * 
	 * On récupère tous les champs, sauf le mot de passe du concessionnaire, et on les attribut à un objet concessionnaireDto,
	 * l'objet concessionnaireDto(ne possédant pas de mot de passe), sera renvoyé en réponse, ou un message d'erreur(exception générée pendant le processus).
	 * 
	 * Les exceptions pouvant etre déclenchées par l'utilisation des controleurs sont rassemblées dans la classe GestionControllerException.
	 * 
	 */
	@PostMapping("authentifierConcessionnaire")
	@ResponseStatus(code=HttpStatus.OK)
	@Operation(description = "Authentification d'un concessionnaire")
//	public Concessionnaire authentifierConcessionnaire(@RequestBody ObjectNode objectNode) {
		public ResponseEntity<ConcessionnaireDto> authentifierConcessionnaire(@RequestBody ObjectNode objectNode) {
		
		
		String email= objectNode.get("email").asText();
		String motDePasse= objectNode.get("password").asText();
	
		Concessionnaire concessionnaire=iConcessionnaireService.authentifierConcessionnaireParEmailMotDePasse(email, motDePasse);
		ConcessionnaireDto concessionnaireDto= new ConcessionnaireDto(concessionnaire.getId(), concessionnaire.getNom(), concessionnaire.getPrenom(), concessionnaire.getEmail(), concessionnaire.getNumeroDeTelephone());
		
		return ResponseEntity.ok(concessionnaireDto);
		
		
		
		//return iConcessionnaireService.authentifierConcessionnaireParEmailMotDePasse(email, motDePasse);
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
