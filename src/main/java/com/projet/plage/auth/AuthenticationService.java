package com.projet.plage.auth;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.plage.config.JwtService;
import com.projet.plage.dao.LocataireDao;
import com.projet.plage.dao.UserRepository;
import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.Locataire;
import com.projet.plage.entity.Role;
import com.projet.plage.entity.User;
import com.projet.plage.mapper.LocataireMapper;
import com.projet.plage.service.ILienDeParenteService;
import com.projet.plage.service.ILocataireService;
import com.projet.plage.service.IPaysService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@Builder

@AllArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	
	private final ILocataireService iLocataireService;
	
	private final LocataireMapper locataireMapper;
	
	private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  AuthenticationManager authenticationManager;
    private final IPaysService iPaysService;
    private final ILienDeParenteService iLienDeParenteService;
    
	public AuthenticationResponse register(RegisterRequest request) {

		var user=User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.CONCESSIONNAIRE)
				.build();
		
		userRepository.save(user);
		var jwtToken= jwtService.generateToken(user);
				
	
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	public AuthenticationResponse registerLocataire(InscriptionRequest request) {
		
		var locataireDto= LocataireDto.builder()
				.nom(request.getNom())
				.prenom(request.getPrenom())
				.email(request.getEmail())
				.password(request.getPassword())
				.pays(iPaysService.recupererPaysParNom(request.getPays()))
				.lienDeParente(iLienDeParenteService.recupererLienDeParenteParNom(request.getLienDeParente()))
		.role(Role.LOCATAIRE)
		.build();
				
		iLocataireService.ajouterLocataire(locataireDto);
		Locataire locataire = locataireMapper.toEntity(locataireDto);
		
		var jwtToken= jwtService.generateToken(locataire);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()
						)
				);
		var user= userRepository.findByEmail(request.getEmail())
				.orElseThrow();
		
		
		var jwtToken=jwtService.generateToken(user);
		
	
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
				
	}
	
	public AuthenticationResponse authenticateLocataire(AuthenticationRequest request) {
		System.out.println(request.getEmail());
		System.out.println(request.getPassword());
	
		
	    Locataire locataire=iLocataireService.recupererLocataireParEmail(request.getEmail());
	    System.out.println(locataire.getEmail());
	    System.out.println(locataire);
	    var jwtToken= jwtService.generateToken(locataire);
	    
	    
	    System.out.println(jwtToken);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
		
	}

}
