package com.medo.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private String imageUrl;
    
    
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    public Clinic() {
        
    }

	public Clinic(Long id, String name, String address, double distanceInKm, String estimatedTime, String imageUrl,
			List<Doctor> doctors) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.distanceInKm = distanceInKm;
		this.estimatedTime = estimatedTime;
		this.imageUrl = imageUrl;
		this.doctors = doctors;
	}

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Clinic [id=" + id + ", name=" + name + ", address=" + address + ", distanceInKm=" + distanceInKm
				+ ", estimatedTime=" + estimatedTime + ", imageUrl=" + imageUrl + ", doctors=" + doctors + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getAddress()=" + getAddress() + ", getDistanceInKm()="
				+ getDistanceInKm() + ", getEstimatedTime()=" + getEstimatedTime() + ", getDoctors()=" + getDoctors()
				+ ", getImageUrl()=" + getImageUrl() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

   
}
