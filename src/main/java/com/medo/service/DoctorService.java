package com.medo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medo.entity.Doctor;
import com.medo.repo.DoctorRepository;


@Service
public class DoctorService {
	
	
	@Autowired
	private  DoctorRepository doctorRepository;
	
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	

	public List<Doctor> getAllDoctors() {

		return doctorRepository.findAll();
	}
	

	public List<Doctor> searchDoctorByName(String name) {

		return doctorRepository.findByNameContainingIgnoreCase(name);
	}

	//time slot
		public List<String> getAvailableSlots(Long doctorId) {
	        Doctor doctor = doctorRepository.findById(doctorId)
	        		.orElseThrow(() -> new RuntimeException("Doctor not found"));
	        return doctor.getAvailableSlots(); 
	    }
}
