package com.solwyz.doctorlab.Repo;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solwyz.doctorlab.Entity.CheckUpCategory;

public interface CheckupRepository extends JpaRepository<CheckUpCategory, Long>{

	static Category save(Category category) {
		return null;
	}

	
}
