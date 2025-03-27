package com.solwyz.doctorlab.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.CheckUpCategory;
import com.solwyz.doctorlab.Repo.CheckupRepository;

@Service
public class CheckUpService {
	
	@Autowired
	private CheckupRepository checkupRepository;

	 public CheckUpCategory createCheckUpCategory(CheckUpCategory category) {
	        return checkupRepository.save(category);
	    }

	 public CheckUpCategory updateCategory(Long id, CheckUpCategory categoryDetails) {
		 CheckUpCategory category = checkupRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinic not found"));
		 category.setTitle(categoryDetails.getTitle());
		 category.setImageUrl(categoryDetails.getImageUrl());
		 //category.setTests(categoryDetails.getTests());
			return checkupRepository.save(category);
		}

		public List<CheckUpCategory> getAllCategory() {
			return checkupRepository.findAll();
		}

		public CheckUpCategory getCategoryById(Long id) {
			return checkupRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
		}

		public void deleteCategory(Long id) {
			checkupRepository.deleteById(id);
		}

	
}
