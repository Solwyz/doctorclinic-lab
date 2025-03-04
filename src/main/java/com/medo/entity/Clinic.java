package com.medo.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clinics")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private double distanceInKm;
    private String estimatedTime; // Estimated travel time

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Doctor> doctors;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getDistanceInKm() {
		return distanceInKm;
	}

	public void setDistanceInKm(double distanceInKm) {
		this.distanceInKm = distanceInKm;
	}

	public String getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

   
}
