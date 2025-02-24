package com.medo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medo.entity.Clinic;
import com.medo.repo.ClinicRepository;

@Service
public class ClinicService {
	
	@Autowired
	private ClinicRepository clinicRepository;

	
	
	public Clinic addClinic(Clinic clinic) {
	    return clinicRepository.save(clinic);
	}



	public List<Clinic> getAllClinics() {
	    return clinicRepository.findAll();
	}


}
