package com.medo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.medo.entity.User;
import com.medo.filters.JwtTokenProvider;
import com.medo.pojo.request.AuthenticationRequest;
import com.medo.pojo.response.AuthenticationResponse;
import com.medo.repo.UserRepository;



@Service
public class  AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

   
    public AuthenticationResponse login(AuthenticationRequest request) {
        String mobileNumber = request.getMobileNumber();
        String otp = request.getOtp();

        // Validate OTP (Static OTP: 12345)
        if (!"12345".equals(otp)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid OTP");
        }

        // Create a new user every time
        User newUser = new User();
        newUser.setMobile(mobileNumber);
        newUser.setRole("USER"); // Assign default role
        newUser.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        // Generate JWT tokens
        String accessToken = jwtTokenProvider.generateAccessToken(savedUser);
        String refreshToken = jwtTokenProvider.generateRefreshToken(savedUser);

        return new AuthenticationResponse("User registered and logged in", savedUser.getId(), accessToken, refreshToken);
    }
    
    public String setMpin(Long id, String mpin) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if (mpin == null || mpin.isEmpty()) {
            throw new IllegalArgumentException("MPIN cannot be empty");
        }

        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("User not found"));

        // Encode and set MPIN
       // user.setMpin(passwordEncoder.encode(mpin));

        // Save to database
        try {
            userRepository.save(user);
            return "MPIN set successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Error saving MPIN: " + e.getMessage());
        }
    }

    }



