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
		
		String[]pathArray= new String[] {"/api/v1/auth/**"};
		//String[]pathArray= new String[] {"/api/v1/auth/**"};
		String[]pathAngular=new String[] {"/api/recupererListeConcessionnaire","/creationLocataire", "/api/locations/create","concessionnaire/liste-location/traitement", "/api/**", "/api/locations/locataireMail/**"};
		String[]pathConcessionnaire=new String[] {"/api/recupererListeLocataire"};
		String[]swaggerArray=new String[] {
				"/v3/api-docs",
	            "/swagger-ui/**",
	            "/v3/api-docs/swagger-config"
				};
		httpSecurity
		.csrf()
		.disable()
		.authorizeHttpRequests()
		/*.antMatchers(swaggerArray)
		.permitAll()
		.antMatchers(pathAngular)
		.permitAll()
		.antMatchers(pathArray)
		.permitAll()
		.anyRequest()
		.authenticated()*/
		.anyRequest()
		.permitAll()
		/*.antMatchers(pathConcessionnaire)
		.hasAuthority("CONCESSIONNAIRE")*/
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		/*.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);*/
		
		return httpSecurity.build();
	}
}
