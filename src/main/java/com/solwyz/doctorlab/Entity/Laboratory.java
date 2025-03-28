package com.solwyz.doctorlab.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.NoArgsConstructor;

@Entity

public class Laboratory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String LabName;
	@Column(name = "ratings")
	private double ratings;
	private String address;
	private double kilometer;
	private int minutes;
	private double price; 
	private double discountPercentage;
	private String imageUrl;
	
	
	@OneToMany(mappedBy = "laboratory", cascade = CascadeType.ALL)
    private List<Test> tests;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabName() {
		return LabName;
	}
	public void setLabName(String labName) {
		LabName = labName;
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
	public double getKilometer() {
		return kilometer;
	}
	public void setKilometer(double kilometer) {
		this.kilometer = kilometer;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public Laboratory(Long id, String labName, double ratings, String address, double kilometer, int minutes,
			double price, double discountPercentage, String imageUrl, List<Test> tests) {
		super();
		this.id = id;
		LabName = labName;
		this.ratings = ratings;
		this.address = address;
		this.kilometer = kilometer;
		this.minutes = minutes;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.imageUrl = imageUrl;
		this.tests = tests;
	}
	public Laboratory() {
		super();
		
	}
	
	
	

}
