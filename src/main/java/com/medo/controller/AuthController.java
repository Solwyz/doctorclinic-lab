package com.medo.controller;


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

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="api/auth")
public class AuthController {
	
	@Autowired
	 private  AuthService authService;
	
	//
	 
	
	 @PostMapping("/login")
     public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
     AuthenticationResponse response = authService.login(request);
     return ResponseEntity.ok(response);   
     
 }
	 
	 @PostMapping("/setmpin")
	 public String setMpin(@RequestParam Long id,  @RequestParam String mpin, @RequestParam String name) {
	     return authService.setMpin(id, mpin, name);
	 }
	 
	 @PostMapping("/validate")
	 public String validatempin(@RequestParam Long id,@RequestParam String mpin) {
		 return authService.validateMpin(id,mpin);
		 
	 }

//logout

}