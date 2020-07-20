package com.giosinosini.springboot3.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {   // generate token
		return Jwts.builder()
			    .setSubject(username)
			    .setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean tokenValid (String token) {
		Claims claims = getClaims(token); 
		if (claims != null) {
			String username = claims.getSubject(); 
			Date expirationToken = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expiration != null && now.before(expirationToken)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUsername (String token) {
		Claims claims = getClaims(token); 
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
			
	private Claims getClaims(String token) { // search claims
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e){
			return null;
		}
	}
	
}
