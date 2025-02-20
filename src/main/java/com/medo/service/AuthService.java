package com.medo.service;

import java.time.LocalDateTime;
import java.util.Optional;

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

        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByMobile(mobileNumber);

        User user;
        if (existingUser.isPresent()) {
            // Existing user, proceed with login
            user = existingUser.get();
        } else {
            // New user, create and save
            user = new User();
            user.setMobile(mobileNumber);
            user.setRole("USER"); // Assign default role
            user.setCreatedAt(LocalDateTime.now());

            user = userRepository.save(user);
        }

        // Generate JWT tokens
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        return new AuthenticationResponse("User logged in successfully", user.getId(), accessToken, refreshToken);
    }

    public String setMpin(Long id, String mpin, String name) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if (mpin == null || mpin.isEmpty()) {
            throw new IllegalArgumentException("MPIN cannot be empty");
        }

        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("User not found"));

        // Set new MPIN and name
        user.setMpin(mpin);  
        user.setName(name);

        try {
            userRepository.save(user);
            return "MPIN and Name updated successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Error updating MPIN and Name: " + e.getMessage());
        }
    }

    }



