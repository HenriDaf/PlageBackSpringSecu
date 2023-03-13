package com.projet.plage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	
	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthentificationFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		String[]pathArray= new String[] {"/api/v1/auth/**", "/api/**"};
		String[]swaggerArray=new String[] {
				"/v3/api-docs",
				"/swagger-ui/**",
				"/v3/api-docs/swagger-config"
		};
		
		
		
		//String[]pathArray= new String[] {"/api/v1/auth/**"};
		String[]pathAngular=new String[] {"/api/recupererListeConcessionnaire","/creationLocataire", "/api/locations/create","concessionnaire/liste-location/traitement", "/api/**"};
		String[]pathConcessionnaire=new String[] {"/api/recupererListeLocataire","/api/locations/liste-location ","/api/locations/liste-location"};
		String[]pathLocataire=new String[] {"/api/locations/locataireMail/**"};
		
		
		
		httpSecurity
		.csrf()
		.disable()
		
		.authorizeHttpRequests()
		.antMatchers("/api/locations/locataireMail/**").permitAll()
		//.antMatchers(pathConcessionnaire).hasAuthority("CONCESSIONNAIRE")
		//.antMatchers(pathLocataire).hasAuthority("LOCATAIRE")
		.antMatchers(swaggerArray)
		.permitAll()
		.antMatchers(pathArray)
		.permitAll()
		//.antMatchers(pathConcessionnaire).permitAll()//.hasAuthority("CONCESSIONNAIRE")
		//.antMatchers(pathLocataire).permitAll()//.hasAuthority("LOCATAIRE")
		.anyRequest()
		.authenticated()
		//.anyRequest()
		//.permitAll()
		/*.antMatchers(pathConcessionnaire)
		.hasAuthority("CONCESSIONNAIRE")*/
		.and()
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider);
		
		
		return httpSecurity.build();
	}
}
