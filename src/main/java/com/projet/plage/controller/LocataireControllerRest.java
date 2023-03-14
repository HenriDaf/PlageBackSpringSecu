package com.projet.plage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.projet.plage.dao.LocataireDao;
import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.Locataire;
import com.projet.plage.service.ILienDeParenteService;
import com.projet.plage.service.ILocataireService;
import com.projet.plage.service.IPaysService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class LocataireControllerRest {
	
	ILocataireService iLocataireService;
	ILienDeParenteService iLienDeParenteService;
	IPaysService iPaysService;	
	LocataireDao dao;
	
	
	/*
	 * On retourne une liste de locataireDto sans le mot de passe, donc lorsqu'on affiche
	 * la liste de locataire, il n'y aura pas le mot de passe.
	 */
	
	//@PreAuthorize("#'Granted Authorities'==CONCESSIONNAIRE")
	@PreAuthorize("hasAuthority('CONCESSIONNAIRE')")
	@GetMapping("recupererListeLocataire")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(description = "récupération de la liste des locataires")
	public List <LocataireDto> getListeLocataires() {
		
		
		List<Locataire> locataires=iLocataireService.recupererLocataire();
		
		
		List<LocataireDto>locataireDtos=new ArrayList<>();
		
		
		locataires.forEach(locataire->locataireDtos.add(new LocataireDto(locataire.getId(), locataire.getNom(), locataire.getPrenom(), locataire.getEmail(),locataire.getLienDeParente(), locataire.getPays())));
		
		
		return locataireDtos;
		
	}
	
		
	
	@PostMapping("creationLocataire")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(description = "ajouter un locataire")
	public Locataire ajouterLocataire(@RequestBody ObjectNode objectNode) {
		
		String email= objectNode.get("email").asText();
		String nom= objectNode.get("nom").asText();
		String prenom= objectNode.get("prenom").asText();
		String password= objectNode.get("password").asText();
		String lienDeParente= objectNode.get("lienDeParente").asText();
		String pays= objectNode.get("pays").asText();
		
		//Locataire locataire=new Locataire(nom, prenom, email, motDePasse, iLienDeParenteService.recupererLienDeParenteParNom(lienDeParente), iPaysService.recupererPaysParNom(pays));
		LocataireDto locataireDto=new LocataireDto(nom, prenom, email, password,iLienDeParenteService.recupererLienDeParenteParNom(lienDeParente), iPaysService.recupererPaysParNom(pays));
	
		//LocataireDto locataireDto=new LocataireDto();	//supprimer le @NoArgs dans locataireDto
		System.out.println(locataireDto);
		return iLocataireService.ajouterLocataire(locataireDto);
		
	}
	
	@DeleteMapping("/supprimerLocataire/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@Operation(description = "supprimer un locataire")
	public Boolean supprimerLocataire(@PathVariable Long id) {
	
		
		
		return iLocataireService.supprimerLocataire(id);
	}

	
	@GetMapping("recupererSel/{email}")
	public byte[] recupererSel(@PathVariable String email) {
		System.out.println("sel recup de la dao");
		System.out.println(dao.findSelByEmail(email));
		return dao.findSelByEmail(email);
		
	}
}
