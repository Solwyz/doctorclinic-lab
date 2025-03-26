package com.solwyz.doctorlab.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.Clinic;
import com.solwyz.doctorlab.Entity.Laboratory;
import com.solwyz.doctorlab.Repo.LabRepository;

@Service
public class LabService {
	
	@Autowired
	private LabRepository labRepository;

	
	public Laboratory createLab(Laboratory lab) {
		return labRepository.save(lab);
	}

	
	 public Laboratory updateLab(Long id, Laboratory labDetails) {
	        Laboratory lab = labRepository.findById(id)
	        		.orElseThrow(() -> new RuntimeException("Lab not found"));
	        
	        lab.setLabName(labDetails.getLabName());
	        lab.setRatings(labDetails.getRatings());
	        lab.setAddress(labDetails.getAddress());
	        lab.setKilometer(labDetails.getKilometer());
	        lab.setMinutes(labDetails.getMinutes());
	        lab.setImageUrl(labDetails.getImageUrl());
	        lab.setPrice(labDetails.getPrice());
	        lab.setDiscountPercentage(labDetails.getDiscountPercentage());
	        
	        return labRepository.save(lab);
	    }

	 public List<Laboratory> getAllLabs() {
			return labRepository.findAll();
		}


	public Laboratory getLabById(Long id) {
		return labRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Lab not found"));
		}



	public void deleteLab(Long id) {
		labRepository.deleteById(id);
		}


	public Laboratory rateLab(Long id, double ratings) {
		Laboratory lab= labRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Lab not found"));
		lab.setRatings(ratings);
		return labRepository.save(lab);
	}

	
	public double getLabRating(Long id) {
		Laboratory lab = labRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Lab not found"));
		return lab.getRatings();
	}

	public List<Laboratory> searchLabs(String name, Double minRating, Double maxRating) {
		return labRepository.findByFilters(name, minRating, maxRating);
	}


	
	


	
	
	
}
