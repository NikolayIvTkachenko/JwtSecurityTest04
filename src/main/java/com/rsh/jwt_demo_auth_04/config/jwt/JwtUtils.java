package com.rsh.jwt_demo_auth_04.config.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.rsh.jwt_demo_auth_04.service.UserDetailsImpl;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtils {

	@Value("${app.jwtSecret}")
	private String jwtSecret;
	
	
	@Value("${app.jwtExpirationMs}")
	private int jwtExperationMs;
	
	@SuppressWarnings("deprecation")
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl)authentication.getPrincipal();
		
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExperationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
	}
	
	public boolean validateJwtToken(String jwt) {
		try {
			
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
			return true;
			
		} catch (MalformedJwtException e) {
			System.err.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public String getUserNameFromJwtToken(String jwt) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
	}
	
}
