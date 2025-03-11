package com.solwyz.doctorlab.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.solwyz.doctorlab.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
class JwtService {
	@Value("${app.token.secret}")
	private String SECRET_KEY;

	public Map<String, String> generateTokens(User user) {
		String token = Jwts.builder().setSubject(user.getMobile()).claim("id", user.getId())
				.claim("name", user.getName()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

		String refreshToken = Jwts.builder().setSubject(user.getMobile()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

		Map<String, String> tokens = new HashMap<>();
		tokens.put("token", token);
		tokens.put("refreshToken", refreshToken);
		return tokens;
	}

	public Claims decodeToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

}