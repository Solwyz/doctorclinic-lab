package com.medo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medo.entity.Doctor;
import com.medo.entity.Patient;
import com.medo.exception.ResourceNotFoundException;
import com.medo.repo.DoctorRepository;
import com.medo.repo.PatientRepository;

@Service
public class PatientService {
//    private final PatientRepository patientRepository;

	@Autowired
	PatientRepository patientRepository;
//    public PatientService(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }

	@Autowired
	private DoctorRepository doctorRepository;

	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public Patient getPatientById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
	}

	public boolean deletePatient(Long id) {

		Optional<Patient> patient = patientRepository.findById(id);
		if (patient.isPresent()) {
			patientRepository.deleteById(id);
			return true;
		}
		return false;

	}

	public Patient editPatient(Long id, Patient updatedPatient) {

		Patient existingPatient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with this id: " + id));

		existingPatient.setName(updatedPatient.getName());
		existingPatient.setAge(updatedPatient.getAge());
		existingPatient.setGender(updatedPatient.getGender());
		existingPatient.setReason(updatedPatient.getReason());

		return patientRepository.save(existingPatient);
	}

	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public List<Doctor> getAllDoctors() {

		return doctorRepository.findAll();
	}

	public List<Doctor> searchDoctorByName(String name) {

		return doctorRepository.findByNameContainingIgnoreCase(name);
	}

}
