package com.solwyz.doctorlab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solwyz.doctorlab.Service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@Tag(name = "User Authentication", description = "APIs for User Authentication related operations")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> checkUser(@RequestParam String mobile) {
		return authService.checkUser(mobile);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestParam String mobile, @RequestParam String name, @RequestParam String otp) {
		return authService.signUp(mobile, name, otp);
	}

	@PostMapping("/verify")
	public ResponseEntity<?> verifyOtp(@RequestParam String mobile, @RequestParam String otp) {
		return authService.verifyOtp(mobile, otp);
	}
}
