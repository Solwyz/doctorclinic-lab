package com.solwyz.doctorlab.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter

public class CheckUpCategory {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String imageUrl;
	
	 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	 private List<Test> tests; 
	 
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public CheckUpCategory(Long id, String title, String imageUrl, List<Test> tests) {
		super();
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.tests = tests;
	}
	public CheckUpCategory() {
		super();
	
	}

}
