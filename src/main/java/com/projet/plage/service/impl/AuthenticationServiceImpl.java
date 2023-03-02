//package com.projet.plage.service.impl;
//
//import org.springframework.stereotype.Service;
//
//import com.projet.plage.controller.auth.AuthenticationRequest;
//import com.projet.plage.controller.auth.AuthenticationResponse;
//import com.projet.plage.controller.auth.RegisterRequest;
//import com.projet.plage.dao.LocataireDao;
//import com.projet.plage.entity.Locataire;
//import com.projet.plage.entity.Role;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationServiceImpl {
//	
//	private final LocataireDao locataireDao;
//	
//	private final PasswordEncoder passwordEncoder;
//	
//	private final JwtServiceImpl jwtService;
//	  
//	private final AuthenticationManager authenticationManager;
//		
//	public AuthenticationResponse register(RegisterRequest request) {
//		var utilisateur =  new Locataire();
//		        utilisateur.setNom(request.getNom());
//		        utilisateur.setPrenom(request.getPrenom());
//		        utilisateur.setEmail(request.getEmail());
//		        utilisateur.setPassword(passwordEncoder.encode(request.getPassword()));
//		        utilisateur.setRole(Role.LOCATAIRE);		        
//		locataireDao.save(utilisateur);
//		
//		var jwtToken = jwtService.generateToken(utilisateur);
//	    return AuthenticationResponse.builder()
//	        .token(jwtToken)
//	        .build();
//	}
//	
//	public AuthenticationResponse authenticate(AuthenticationRequest request) {
//		authenticationManager.authenticate(
//		new UsernamePasswordAuthenticationToken(
//	            request.getEmail(),
//	            request.getPassword()
//	        )
//	    );
//	    var utilisateur =  locataireDao.findByEmail(request.getEmail())
//	        .orElseThrow();
//	    var jwtToken = jwtService.generateToken(utilisateur);
//	    return AuthenticationResponse.builder()
//	        .token(jwtToken)
//	        .build();
//	}
//	
//}
