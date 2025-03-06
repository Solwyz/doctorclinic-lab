package com.medo.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doctor_availability")
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "available_date", nullable = false)
    private LocalDate availableDate;

    @ElementCollection
    @CollectionTable(name = "doctor_slots", joinColumns = @JoinColumn(name = "availability_id"))
    @Column(name = "slot")
    private List<String> availableSlots;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}

	public List<String> getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(List<String> availableSlots) {
		this.availableSlots = availableSlots;
	}

	public DoctorAvailability(Long id, Doctor doctor, LocalDate availableDate, List<String> availableSlots) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.availableDate = availableDate;
		this.availableSlots = availableSlots;
	}

	public DoctorAvailability() {
		super();
		// TODO Auto-generated constructor stub
	}  

  
}
