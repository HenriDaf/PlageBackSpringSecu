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


@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
	System.out.println(authHeader);
		final String jwt;
		final String userEmail;
		if(authHeader  == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt= authHeader.substring(7);
		System.out.println(jwt);
		userEmail= jwtService.extractUsername(jwt); //extract userEmail from JWT token;
		System.out.println(userEmail);
		
		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails= this.userDetailsService.loadUserByUsername(userEmail);
			System.out.println(userDetails.getAuthorities());
			System.out.println(jwtService.isTokenValid(jwt, userDetails));
			if(jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				System.out.println(authenticationToken);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
