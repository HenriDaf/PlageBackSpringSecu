package com.projet.plage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projet.plage.entity.Salage;
import com.projet.plage.service.ISalageService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class SalageControllerRest {

	ISalageService iSalageService;

	@GetMapping("recupererListeSalage")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(description = "Afficher la liste des salages")
	List<Salage> recupererListe() {

		return iSalageService.recupererListe();
	}
	
	
	@DeleteMapping("supprimerSalage/{id}")
	Boolean supprimerSalage(@PathVariable Long id) {
		return iSalageService.supprimerSalage(id);
	}

}
