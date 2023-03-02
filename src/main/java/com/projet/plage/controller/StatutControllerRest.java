package com.projet.plage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projet.plage.entity.Statut;
import com.projet.plage.service.IStatutService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
/**
 * Contrôleur qui gère la gestion des statuts pour une location
 * @author Julien
 *
 */
public class StatutControllerRest {
	
	private final IStatutService statutService;
	
	/**
	 * Cette méthode récupère une liste de statuts.
	 * @return List<Statut>
	 */
	@GetMapping("liste-des-statuts")
	@ResponseStatus(code=HttpStatus.OK)
	@Operation(description = "Afficher la liste des statuts")
	List<Statut> recupererPays(){
		
		return statutService.recupererStatuts();
	}

}
