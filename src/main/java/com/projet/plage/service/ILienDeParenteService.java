package com.projet.plage.service;

import java.util.List;

import com.projet.plage.dto.LienDeParenteDto;
import com.projet.plage.entity.LienDeParente;

public interface ILienDeParenteService {
	
	List<LienDeParente> recupererLienDeParente();
	
	LienDeParente recupererLienDeParenteParNom(String nom);
	
	//LienDeParente ajouterLienDeParente(LienDeParente lienDeParente);
	
	LienDeParente ajouterLienDeParente(LienDeParenteDto lienDeParenteDto);
	
	LienDeParente modifiLienDeParente(LienDeParenteDto lienDeParenteDto);
	
	Boolean supprimerLienDeParenteParId(Long id);
	
	Boolean supprimerLienDeParenteParNom(String nom);
	
	//LienDeParente modifierLienDeParenteCoefficient(float coefficient);
	

}
