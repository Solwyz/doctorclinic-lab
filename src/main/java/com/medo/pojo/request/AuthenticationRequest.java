package com.medo.pojo.request;



import lombok.Data;

@Data


public class AuthenticationRequest {
    
    private String mobileNumber;  // Instead of username
    private String otp;  // Instead of password/mpin

    // Getters and Setters
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }
}