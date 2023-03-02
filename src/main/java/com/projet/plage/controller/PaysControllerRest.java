package com.projet.plage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import com.projet.plage.entity.Pays;
import com.projet.plage.service.IPaysService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class PaysControllerRest {
	
	IPaysService iPaysService;
	
	
	@GetMapping("listePays")
	@ResponseStatus(code=HttpStatus.OK)
	@Operation(description = "Affichage de la liste de pays")
	List<Pays> recupererPays(){
		
		return iPaysService.recupererPays();
	}
	
	
	@PostMapping("recupererPaysParNom")
	@ResponseStatus(code=HttpStatus.OK)
	public Pays recupererPaysParNom(@RequestBody ObjectNode objectNode){
		
		String nom = objectNode.get("pays").asText();
		
	return iPaysService.recupererPaysParNom(nom);	
	}
	
	
	

}
