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
		
		String[]pathArray= new String[] {"/api/v1/auth/**", "/api/**", "/api/locations/liste-location"};
		String[]pathAngular=new String[] {"/recupererListeConcessionnaire","/creationLocataire"};
		String[]swaggerArray=new String[] {
				"/v3/api-docs",
	            "/swagger-ui/**",
	            "/v3/api-docs/swagger-config"
				};
		httpSecurity
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(swaggerArray)
		.permitAll()
		.antMatchers(pathAngular)
		.permitAll()
		.antMatchers(pathArray)
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		httpSecurity.cors();
		return httpSecurity.build();
	}
}
