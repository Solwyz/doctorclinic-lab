package com.medo.entity;

import java.time.LocalDate;
import javax.persistence.*;

import com.medco.Enum.AppointmentStatus;

@Entity
@Table(name = "appointments") // Added table name for clarity
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "user_id", nullable = false)
    private Long userId; 

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;  

    @Column(name = "slot", nullable = true)
    private String slot; // Stores the available slot  

    @Enumerated(EnumType.STRING)  
    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @Column(name = "rating", nullable = true)
    private Integer rating; // Rating (1-5 stars)

    @Column(name = "feedback", columnDefinition = "TEXT", nullable = true)
    private String feedback; // User's written feedback

    public Appointment() {
        super();
    }

    public Appointment(Long id, Patient patient, Doctor doctor, Long userId, LocalDate appointmentDate, String slot, AppointmentStatus status, Integer rating, String feedback) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.userId = userId;
        this.appointmentDate = appointmentDate;
        this.slot = slot;
        this.status = status;
        this.rating = rating;
        this.feedback = feedback;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
