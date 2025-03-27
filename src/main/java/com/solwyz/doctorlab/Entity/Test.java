package com.solwyz.doctorlab.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String testName;
	private String testDetails;
	private int testCount;
	
	@ManyToOne
    private Laboratory laboratory;

	@ManyToOne
    private CheckUpCategory category;
	

	public int getTestCount() {
		return testCount;
	}
	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}
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

	

}
