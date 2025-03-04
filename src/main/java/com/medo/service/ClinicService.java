package com.medo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medo.entity.Clinic;
import com.medo.entity.Patient;
import com.medo.entity.Report;
import com.medo.repo.ClinicRepository;
import com.medo.repo.PatientRepository;
import com.medo.repo.ReportRepository;

@Service
public class ClinicService {
	
	@Autowired
	private ClinicRepository clinicRepository;

	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	
	@Transactional
	public Clinic addClinic(Clinic clinic) {
		 // System.out.println("Saving Clinic: " + clinic.getName() + ", " + clinic.getAddress());
	    return clinicRepository.save(clinic);
	}



	public List<Clinic> getAllClinics() {
	    return clinicRepository.findAll();
	}



	public List<Report> getPatientReports(Long patientId) {
		
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return reportRepository.findByPatientOrderByCreatedDateDesc(patient);
    }


}
