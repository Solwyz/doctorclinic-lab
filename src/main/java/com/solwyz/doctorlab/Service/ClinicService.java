package com.solwyz.doctorlab.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.Clinic;
import com.solwyz.doctorlab.Repo.ClinicRepository;

@Service
public class ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;

	public Clinic createClinic(Clinic clinic) {
		return clinicRepository.save(clinic);
	}

	public Clinic updateClinic(Long id, Clinic clinicDetails) {
		Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinic not found"));
		clinic.setClinicName(clinicDetails.getClinicName());
		clinic.setRatings(clinicDetails.getRatings());
		clinic.setAddress(clinicDetails.getAddress());
		clinic.setKilometer(clinicDetails.getKilometer());
		clinic.setMinutes(clinicDetails.getMinutes());
		clinic.setImageUrl(clinicDetails.getImageUrl());
		return clinicRepository.save(clinic);
	}

	public List<Clinic> getAllClinics() {
		return clinicRepository.findAll();
	}

	public Clinic getClinicById(Long id) {
		return clinicRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinic not found"));
	}

	public void deleteClinic(Long id) {
		clinicRepository.deleteById(id);
	}

	public Clinic rateClinic(Long id, double rating) {
		Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinic not found"));
		clinic.setRatings(rating);
		return clinicRepository.save(clinic);
	}

	public double getClinicRating(Long id) {
		Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinic not found"));
		return clinic.getRatings();
	}

	public List<Clinic> searchClinics(String name, Double minRating, Double maxRating) {
		return clinicRepository.findByFilters(name, minRating, maxRating);
	}


}
