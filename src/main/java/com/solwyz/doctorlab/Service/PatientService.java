package com.solwyz.doctorlab.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.solwyz.doctorlab.Entity.Patient;
import com.solwyz.doctorlab.Repo.PatientRepository;

public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public Patient createPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public Patient updatePatient(Long id, Patient patientDetails) {
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
		patient.setName(patientDetails.getName());
		patient.setAge(patientDetails.getAge());
		patient.setProblem(patientDetails.getProblem());
		return patientRepository.save(patient);
	}

	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}

	public Patient getPatientById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
	}

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

}
