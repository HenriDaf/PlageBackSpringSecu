//package com.projet.plage.service.impl;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//@Service
//public class JwtServiceImpl {
//	
//	private static final String SECRET_KEY = "73357538782F413F4428472B4B6250655368566D597133743677397924422645";
//	
//	/**
//	 * Extraire le mail du token jwt
//	 * @param jwt
//	 * @return
//	 */
//	public String extractEmail(String jwt) {		
//		return extractClaim(jwt, Claims::getSubject);		
//	}
//	
//	/**
//	 * Extraire les autres données
//	 * @param <T>
//	 * @param token
//	 * @param claimsResolvers
//	 * @return
//	 */
//	public <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
//		final Claims claims = extractAllClaims(token);
//		return claimsResolvers.apply(claims);		
//	}
//	
//	/**
//	 * Extraire les parties du token
//	 * @param token
//	 * @return
//	 */
//	private Claims extractAllClaims(String token) {
//		return Jwts.parserBuilder()
//				.setSigningKey(getSignInKey())
//				.build()
//				.parseClaimsJws(token)
//				.getBody();
//	}
//	
//	public String generateToken(UserDetails userDetails) {
//		return generateToken(new HashMap<>(), userDetails);
//	}
//	
//	/**
//	 * Génération  d'un token
//	 * @param contents
//	 * @param userDetails
//	 * @return
//	 */
//	public String generateToken(Map<String, Object> contents, UserDetails userDetails) {
//		return Jwts.builder().setClaims(contents).setSubject(userDetails.getUsername())
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 2))
//				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
//				.compact();
//	}
//	
//	public boolean isTokenValid(String token, UserDetails userDetails) {
//		final String email = extractEmail(token);
//		return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
//	}
//	
//	/**
//	 * Vérification de la date du token
//	 * @param token
//	 * @return
//	 */
//	private boolean isTokenExpired(String token) {
//		return  extractExpiration(token).before(new Date());
//	}
//	
//	private Date extractExpiration(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//	
//	/**
//	 * Décoder le token jwt
//	 * @return
//	 */
//	private Key getSignInKey() {		
//		byte[] cle = Decoders.BASE64.decode(SECRET_KEY);
//		return Keys.hmacShaKeyFor(cle);
//	}
//
//}
