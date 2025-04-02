package com.solwyz.doctorlab.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.Doctor;
import com.solwyz.doctorlab.Repo.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	public Doctor createDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public Doctor updateDoctor(Long id, Doctor doctorDetails) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
		doctor.setName(doctorDetails.getName());
		doctor.setSpecialization(doctorDetails.getSpecialization());
		doctor.setImage(doctorDetails.getImage());
		doctor.setFees(doctorDetails.getFees());
		doctor.setReview(doctorDetails.getReview());
		doctor.setClinic(doctorDetails.getClinic());
		doctor.setAbout(doctorDetails.getAbout());
		doctor.setAvailabilityTimes(doctorDetails.getAvailabilityTimes());
		doctor.setYearsOfExperience(doctorDetails.getYearsOfExperience());
		doctor.setRatings(doctorDetails.getRatings());
		doctor.setGender(doctorDetails.getGender());
		doctor.setDepartment(doctorDetails.getDepartment());
		doctor.setLanguages(doctorDetails.getLanguages());
		return doctorRepository.save(doctor);
	}

	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}

	public List<Doctor> getDoctorsByClinic(Long clinicId) {
		return doctorRepository.findByClinicId(clinicId);
	}

	public List<Doctor> getDoctorsByDepartment(String department) {
		return doctorRepository.findByDepartment(department);
	}
	
//	public List<Doctor> getDoctorsByDepartmentId(Long departmentId) {
//	    return doctorRepository.findByDepartmentId(departmentId);
//	}



	public List<Doctor> searchDoctors(String name, Double minRating, Double maxRating, String availabilityTimes) {
		return doctorRepository.findByFilters(name, minRating, maxRating, availabilityTimes);
	}

	public Doctor getDoctorById(Long id) {
		return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
	}

	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

}
