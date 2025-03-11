package com.medo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medo.pojo.request.AuthenticationRequest;
import com.medo.pojo.response.AuthenticationResponse;
import com.medo.service.AuthService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String mobile, @RequestParam(required = false) String mpin) {
		return authService.login(mobile, mpin);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestParam String mobile, @RequestParam String name) {
		return authService.signup(mobile, name);
	}

	@PostMapping("/set-mpin")
	public ResponseEntity<?> setMpin(@RequestParam String mobile, @RequestParam String mpin) {
		return authService.setMpin(mobile, mpin);
	}

	@PostMapping("/validate-mpin")
	public ResponseEntity<?> validateMpin(@RequestParam String mobile, @RequestParam String mpin) {
		return authService.validateMpin(mobile, mpin);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> signOut(@RequestParam String mobile) {
		return authService.signOut(mobile);
	}
}