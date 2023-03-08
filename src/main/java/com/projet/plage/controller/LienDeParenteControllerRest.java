package com.projet.plage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projet.plage.entity.LienDeParente;
import com.projet.plage.service.ILienDeParenteService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@Validated
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class LienDeParenteControllerRest {
	
	ILienDeParenteService lienParentService;
	
	@GetMapping("listeLienParente")
	@ResponseStatus(code=HttpStatus.OK)
	@Operation(description = "Affichage des liens de parenté")
	List<LienDeParente> recupererPays(){
		
		return lienParentService.recupererLienDeParente();
	}

}
