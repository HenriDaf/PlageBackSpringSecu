package com.projet.plage.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author Henri.
 * 
 * Classe annotée @ControllerAdvice, cette classe centralise toutes les exceptions pouvant etre déclenchées par les controlleurs rest.
 * Les exceptions de type violation de contraintes, ou les exceptions maisons.
 * 
 * Cela permet de ne pas avoir à surcharger chaque controlleur rest, avec le code de gestion des exceptions pouvant y etre déclenchées.
 *
 */

@ControllerAdvice
public class GestionControllerException {
	

	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<Object> constraintViolationHandlerException(ConstraintViolationException e){
		
		final List<String> erreurs= new ArrayList<>();
		
		e.getConstraintViolations().forEach(violation->erreurs.add(violation.getMessage()));
		
		return new ResponseEntity<>(erreurs, HttpStatus.BAD_REQUEST);
		
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 * 
	 * Liste des exceptions maisons pouvant etre déclenchées par les méthodes des différents controlleurs rest.
	 */

	@ExceptionHandler(value= {UtilisateurDejaExistantException.class,LienDeParenteNonTrouve.class,
			PaysNonTrouveException.class, UtilisateurNonTrouveException.class, UtilisateurNonSupprimable.class
	})
	ResponseEntity<Object>  utilisateurDejaExistantException(Exception e){
		
		final String erreur= e.getMessage();
		
		return new ResponseEntity<>(erreur, HttpStatus.BAD_REQUEST); 
		//return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); 
		
	}

}
