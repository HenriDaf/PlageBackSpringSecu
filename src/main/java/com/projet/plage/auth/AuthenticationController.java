package com.projet.plage.auth;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projet.plage.dao.UserRepository;
import com.projet.plage.entity.User;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor

public class AuthenticationController {

	private final AuthenticationService authenticationService;
	private final UserRepository userRepository; 
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest request){
	
		return ResponseEntity.ok(authenticationService.register(request));
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse>authenticate(@RequestBody AuthenticationRequest request){
		
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
	
	@GetMapping("listUsers")
	public ResponseEntity<List<User>> recupererListeUsers(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@PostMapping("/inscriptionLocataire")
	
	public ResponseEntity<AuthenticationResponse>registerLocataire(@RequestBody InscriptionRequest request){
		return ResponseEntity.ok(authenticationService.registerLocataire(request));
		
	}
	
	@PostMapping("/authentificationLocataire")
	public ResponseEntity<AuthenticationResponse>authenticateLocataire(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(authenticationService.authenticateLocataire(request));
		
		/*
		 * Possibilité de renvoyer un objet DTO avec nom prenom email mdp dans le ResponseEntity
		 * et du coup il faudrait peut ajouter le token dans cet objet
		 */
		
	}
	
	@PostMapping("/inscriptionConcessionnaire")
	public ResponseEntity<AuthenticationResponse>registerConcessionnaire(@RequestBody ConcessionnaireInscriptionRequest request){
		return ResponseEntity.ok(authenticationService.registerConcessionnaire(request));
		
	}
	
	@PostMapping("/authentificationConcessionnaire")
	public ResponseEntity<AuthenticationResponse> authenticateConcessionnaire(@RequestBody AuthenticationRequest request){
		
		return ResponseEntity.ok(authenticationService.authenticateConcessionnaire(request));
	}
	

	
	
	
}
