package com.solwyz.doctorlab.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String clinicName;
	@Column(name = "ratings", nullable = false)
	private double ratings;
	private String address;
	private double kilometer;
	private int minutes;
	private String imageUrl;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public double getRatings() {
		return ratings;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getKilometer() {
		return kilometer;
	}

	public void setKilometer(double kilometer) {
		this.kilometer=kilometer;
	}
	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	@Override
	public String toString() {
		return "Clinic [id=" + id + ", clinicName=" + clinicName + ", ratings=" + ratings + ", address=" + address
				+ ", kilometer=" + kilometer + ", minutes=" + minutes + ", imageUrl=" + imageUrl + ", getId()="
				+ getId() + ", getClinicName()=" + getClinicName() + ", getRatings()=" + getRatings()
				+ ", getAddress()=" + getAddress() + ", getImageUrl()=" + getImageUrl() + ", getKilometer()="
				+ getKilometer() + ", getMinutes()=" + getMinutes() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}


}
