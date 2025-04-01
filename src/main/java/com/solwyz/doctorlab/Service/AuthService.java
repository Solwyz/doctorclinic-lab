package com.solwyz.doctorlab.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.User;
import com.solwyz.doctorlab.Repo.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	private Map<String, String> otpStorage = new HashMap<>();

	public ResponseEntity<?> checkUser(String mobile) {
		User user = userRepository.findByMobile(mobile);
		String otp = "123456"; 
		otpStorage.put(mobile, otp);
		sendOtpToUser(mobile, otp);

		if (user != null) {
			return ResponseEntity.ok("OTP sent. Verify to login.");
		} else {
			return ResponseEntity.ok("New user. Please provide name.");
		}
	}

	public ResponseEntity<?> signUp(String mobile, String name, String otp) {
		if (!otp.equals("123456")) { 
			return ResponseEntity.badRequest().body("Invalid OTP");
		}

		User newUser = new User();
		newUser.setMobile(mobile);
		newUser.setName(name);
		userRepository.save(newUser);

		Map<String, String> tokens = jwtService.generateTokens(newUser);
		return ResponseEntity.ok(tokens);
	}

	public ResponseEntity<?> verifyOtp(String mobile, String otp) {
		if (!otp.equals("123456")) { 
			return ResponseEntity.badRequest().body("Invalid OTP");
		}

		User user = userRepository.findByMobile(mobile);
		if (user == null) {
			return ResponseEntity.badRequest().body("User not found");
		}

		Map<String, String> tokens = jwtService.generateTokens(user);
		return ResponseEntity.ok(tokens);
	}

	private void sendOtpToUser(String mobile, String otp) {
		System.out.println("OTP for " + mobile + " is " + otp);
	}
}
