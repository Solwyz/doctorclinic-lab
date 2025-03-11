package com.medo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medo.entity.User;
import com.medo.repo.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	private static final String DEFAULT_OTP = "123456";
	private static final String SECRET_KEY = "mySecretKey";

	public ResponseEntity<?> login(String mobile, String mpin) {
		User user = userRepository.findByMobile(mobile);
		if (user != null) {
			if (mpin != null && user.getMpin().equals(mpin)) {
				user.setActive(true);
				userRepository.save(user);
				String token = generateToken(user.getId());
				return ResponseEntity.ok("Login successful. JWT: " + token);
			} else {
				return ResponseEntity.status(401).body("Invalid MPIN");
			}
		}
		return ResponseEntity.status(404).body("User not found. Redirect to signup");
	}

	public ResponseEntity<?> signup(String mobile, String name) {
		if (userRepository.existsByMobile(mobile)) {
			return ResponseEntity.status(400).body("User already exists");
		}
		User newUser = new User(mobile, name, null);
		userRepository.save(newUser);
		return ResponseEntity.ok("OTP sent: " + DEFAULT_OTP);
	}

	public ResponseEntity<?> setMpin(String mobile, String mpin) {
		User user = userRepository.findByMobile(mobile);
		if (user != null) {
			user.setMpin(mpin);
			userRepository.save(user);
			return ResponseEntity.ok("MPIN set successfully");
		}
		return ResponseEntity.status(404).body("User not found");
	}

	public ResponseEntity<?> validateMpin(String mobile, String mpin) {
		User user = userRepository.findByMobile(mobile);
		if (user != null) {
			return user.getMpin().equals(mpin) ? ResponseEntity.ok("MPIN is valid")
					: ResponseEntity.status(401).body("Invalid MPIN");
		}
		return ResponseEntity.status(404).body("User not found");
	}

	public ResponseEntity<?> signOut(String mobile) {
		User user = userRepository.findByMobile(mobile);
		if (user != null && user.isActive()) {
			user.setActive(false);
			userRepository.save(user);
			return ResponseEntity.ok("Signout successful");
		}
		return ResponseEntity.status(400).body("User not logged in");
	}

	private String generateToken(Long userId) {
		return Jwts.builder().setSubject(String.valueOf(userId)).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
}
