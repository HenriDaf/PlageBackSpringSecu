package com.projet.plage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projet.plage.dao.ConcessionnaireDao;
import com.projet.plage.dao.LocataireDao;
import com.projet.plage.exception.UtilisateurNonTrouveException;

import lombok.AllArgsConstructor;


@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true
		)

@AllArgsConstructor
public class ApplicationConfig {

	// private final UserRepository repository;
	private final LocataireDao locataireDao;
	private final ConcessionnaireDao concessionnaireDao;

	@Bean
	public UserDetailsService userDetailsService() {
		/*
		 * return username -> repository.findByEmail(username) .orElseThrow(()->new
		 * UsernameNotFoundException("User not found"));
		 */

	return username -> {
		
			if (concessionnaireDao.findByEmail(username) != null) {
		
				return concessionnaireDao.findByEmail(username);
			}
			if (locataireDao.findByEmail(username) != null) {
			
				return locataireDao.findByEmail(username);
			}
			throw new UtilisateurNonTrouveException("Échec  de l'authentification, identifiants incorrects");

		};

		// return username-> locataireDao.findByEmail(username);
		// return locataireDao::findByEmail;

	//	return concessionnaireDao::findByEmail;
	}
		
		

	@Bean
	public AuthenticationProvider authentificationProvider() {
	
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	
		authenticationProvider.setUserDetailsService(userDetailsService());
	
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
	
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

}
