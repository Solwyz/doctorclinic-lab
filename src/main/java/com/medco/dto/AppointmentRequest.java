package com.medco.dto;

import java.time.LocalDate;

public class AppointmentRequest {
    private Long doctorId;
    private Long patientId;
    private Long userId;
    private LocalDate availableDate;  // Ensure this matches Doctor entity's availableDate
    private String availableSlot;     // Ensure this matches the slots in Doctor entity

    // Constructors
    public AppointmentRequest() {}

    public AppointmentRequest(Long doctorId, Long patientId, Long userId, LocalDate availableDate, String availableSlot) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.userId = userId;
        this.availableDate = availableDate;
        this.availableSlot = availableSlot;
    }

    // Getters and Setters
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public String getAvailableSlot() {
        return availableSlot;
    }

    public void setAvailableSlot(String availableSlot) {
        this.availableSlot = availableSlot;
    }
}
