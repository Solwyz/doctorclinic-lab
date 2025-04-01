package com.solwyz.doctorlab.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table

public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String testName;
	private String testDetails;
	private int patientCount;
	private double amount;
	private double sampleCollectionCharge;
	private double totalAmount;

	@PrePersist
	@PreUpdate
	private void calculateTotalAmount() {

		this.totalAmount = (this.amount + this.sampleCollectionCharge) * this.patientCount;
	}

	@ManyToOne
	@JoinColumn(name = "laboratory_id")
	private Laboratory laboratory;

	@ManyToOne
	@JoinColumn(name = "category_id")

	private CheckUpCategory category;

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	public CheckUpCategory getCategory() {
		return category;
	}

	public void setCategory(CheckUpCategory category) {
		this.category = category;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getSampleCollectionCharge() {
		return sampleCollectionCharge;
	}

	public void setSampleCollectionCharge(double sampleCollectionCharge) {
		this.sampleCollectionCharge = sampleCollectionCharge;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPatientCount() {
		return patientCount;
	}

	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}

}
