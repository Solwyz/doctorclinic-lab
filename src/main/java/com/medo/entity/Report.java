package com.medo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false) 
        private Patient patient;

        @ManyToOne
        @JoinColumn(name = "doctor_id") 
        private Doctor doctor;

        @ManyToOne
        @JoinColumn(name = "lab_id") 
        private Clinic clinic;

        private String reportType;
        private String reportUrl;
        private LocalDateTime createdDate;
        
        
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
		public Clinic getClinic() {
			return clinic;
		}
		public void setClinic(Clinic clinic) {
			this.clinic = clinic;
		}
		public String getReportType() {
			return reportType;
		}
		public void setReportType(String reportType) {
			this.reportType = reportType;
		}
		public String getReportUrl() {
			return reportUrl;
		}
		public void setReportUrl(String reportUrl) {
			this.reportUrl = reportUrl;
		}
		public LocalDateTime getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}

      
    }

   
