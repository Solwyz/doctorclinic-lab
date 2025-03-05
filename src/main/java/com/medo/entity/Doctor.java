package com.medo.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    private String qualification;

    @ElementCollection
    private List<String> languages;  

    private double amount;
    private String experience;
    
    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false)
    @JsonBackReference
    private Clinic clinic; 
    
    private String about;
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "doctor_available_slots", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "slot")
    private List<String> availableSlots;
    
    @Column(name = "appointment_date", nullable = false)
    private LocalDate availableDate;  

    @Column(nullable = false)
    private Double rating = 0.0;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    // Remove reviewCount field from entity since it's computed dynamically

    public int getReviewCount() {
        return reviews.size(); // Always return the latest review count
    }

    public Double getAverageRating() {
        return reviews.isEmpty() ? 0.0 : reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

