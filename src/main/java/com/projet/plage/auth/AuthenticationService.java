package com.projet.plage.auth;


import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.plage.config.JwtService;
import com.projet.plage.dao.ConcessionnaireDao;
import com.projet.plage.dao.LocataireDao;
import com.projet.plage.dao.UserRepository;
import com.projet.plage.dto.ConcessionnaireDto;
import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.Concessionnaire;
import com.projet.plage.entity.Locataire;
import com.projet.plage.entity.Role;
import com.projet.plage.entity.User;
import com.projet.plage.mapper.ConcessionnaireMapper;
import com.projet.plage.mapper.LocataireMapper;
import com.projet.plage.service.IConcessionnaireService;
import com.projet.plage.service.ILienDeParenteService;
import com.projet.plage.service.ILocataireService;
import com.projet.plage.service.IPaysService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Service
@Builder
@Slf4j
@AllArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	
	private final ConcessionnaireDao concessionnaireDao;
	
	private final ILocataireService iLocataireService;
	private final LocataireDao locataireDao;
	
	private final LocataireMapper locataireMapper;
	
	private final IConcessionnaireService iConcessionnaireService;
	private final ConcessionnaireMapper concessionnaireMapper;
	
	private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final  AuthenticationManager authenticationManager;
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
				.password(passwordEncoder.encode(request.getPassword()))
				.pays(iPaysService.recupererPaysParNom(request.getPays()))
				.lienDeParente(iLienDeParenteService.recupererLienDeParenteParNom(request.getLienDeParente()))
		.role(Role.LOCATAIRE)
		.build();
				
		
		/*LocataireDto locataireDto= new LocataireDto(null, request.getNom(), request.getPrenom(), request.getEmail(), request.getPassword(),iLienDeParenteService.recupererLienDeParenteParNom(request.getLienDeParente()), iPaysService.recupererPaysParNom(request.getPays()), Role.LOCATAIRE);
	//LocataireDto locataireDto= new LocataireDto(null, null, null, null, null, null, null, null);
		locataire.setRole(Role.LOCATAIRE);*/
		
		//	iLocataireService.ajouterLocataire(locataireDto);
		Locataire locataire = locataireMapper.toEntity(locataireDto);
		
		locataireDao.save(locataire);
		
		var jwtToken= jwtService.generateToken(locataire);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
	}

	
	public AuthenticationResponse registerConcessionnaire(ConcessionnaireInscriptionRequest request) {
		
		/*var concessionnaireDto=ConcessionnaireDto.builder()
				.nom(request.getNom())
				.prenom(request.getPrenom())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.numeroDeTelephone(request.getNumeroDeTelephone())
				.role(Role.CONCESSIONNAIRE)
				.build();*/
		
		ConcessionnaireDto concessionnaireDto= new ConcessionnaireDto(null, request.getNom(), request.getPrenom(), request.getEmail(), request.getNumeroDeTelephone(),passwordEncoder.encode(request.getPassword()), Role.CONCESSIONNAIRE);
		
		System.out.println(request.getPassword());
		System.out.println("password: "+concessionnaireDto.getPassword());
		System.out.println("telephone "+concessionnaireDto.getNumeroDeTelephone());
		//iConcessionnaireService.ajouterConcessionnaire(concessionnaireDto);*/
		Concessionnaire concessionnaire=concessionnaireMapper.toEntity(concessionnaireDto);
	//	System.out.println(concessionnaire);
		//log.warn(concessionnaire.toString());
		concessionnaireDao.save(concessionnaire);
		var jwtToken=jwtService.generateToken(concessionnaire);
		
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
		
		
	
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()
						)
				);
		
		System.out.println(request.getEmail());
	    Locataire locataire=iLocataireService.recupererLocataireParEmail(request.getEmail());
	    
	    var jwtToken= jwtService.generateToken(locataire);
	    
	    
	
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
		
	}
	
	public AuthenticationResponse authenticateConcessionnaire(AuthenticationRequest request) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()
						)
				);
		System.out.println(request.getEmail());
		Concessionnaire concessionnaire= iConcessionnaireService.recupererParEmail(request.getEmail());
		
		var jwtToken= jwtService.generateToken(concessionnaire);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

}
