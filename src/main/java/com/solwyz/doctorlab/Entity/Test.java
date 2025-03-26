package com.solwyz.doctorlab.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String testName;
	private String testDetails;
	private double price;
	
	@ManyToOne
    private Laboratory laboratory;

	 public Test() {
	    }
	 
	public Test(Long id, String testName, String testDetails, double price) {
		super();
		this.id = id;
		this.testName = testName;
		this.testDetails = testDetails;
		this.price = price;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestDetails() {
		return testDetails;
	}
	public void setTestDetails(String testDetails) {
		this.testDetails = testDetails;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}
