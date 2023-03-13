package com.projet.plage.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		
		log.info("");
		log.info("");
		log.info("--------------------------------------------------------------------------------------------");
		log.info(request.toString());
		final String authHeader = request.getHeader("Authorization");
	log.info("authHeader: "+authHeader);
		final String jwt;
		final String userEmail;
		if(authHeader  == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt= authHeader.substring(7);
		log.info("jwt: "+jwt);
		userEmail= jwtService.extractUsername(jwt); //extract userEmail from JWT token;
		log.info("userEmail: "+userEmail);
		
		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails= this.userDetailsService.loadUserByUsername(userEmail);
			log.info("authorities: "+userDetails.getAuthorities());
	
			log.info("isValid: "+jwtService.isTokenValid(jwt, userDetails));
			if(jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//System.out.println("authenti token "+authenticationToken);

				log.info("");
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
