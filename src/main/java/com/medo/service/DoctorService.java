package com.medo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medo.entity.Clinic;
import com.medo.entity.Doctor;
import com.medo.entity.DoctorAvailability;
import com.medo.entity.Search;
import com.medo.exception.ClinicNotFoundException;
import com.medo.exception.DoctorNotFoundException;
import com.medo.repo.ClinicRepository;
import com.medo.repo.DoctorAvailabilityRepository;
import com.medo.repo.DoctorRepository;


@Service
public class DoctorService {
	
	
	@Autowired
	private  DoctorRepository doctorRepository;
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private DoctorAvailabilityRepository doctorAvailabilityRepository;

	
	 public Doctor addDoctor(Doctor doctor) {
	        // Get clinicId from request body
	        Long clinicId = doctor.getClinic().getId();
	        
	        // Fetch full clinic details from the database
	        Clinic clinic = clinicRepository.findById(clinicId)
	                .orElseThrow(() -> new RuntimeException("Clinic not found"));

	        // Set clinic and default review count
	        doctor.setClinic(clinic);
	        doctor.setReviewCount(0);

	        return doctorRepository.save(doctor);
	    }


	public List<Doctor> getAllDoctors() {

		return doctorRepository.findAll();
	}
	

	
	
	 public Doctor getDoctorById(Long doctorId) {
	        return doctorRepository.findById(doctorId)
	                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with ID: " + doctorId));
	    }
	
//	 public List<Doctor> searchDoctors(String name, String department) {
//		    if (name != null && department != null) {
//		        return doctorRepository.findByNameStartingWithIgnoreCaseAndDepartmentStartingWithIgnoreCase(name, department);
//		    } else if (name != null) {
//		        return doctorRepository.findByNameStartingWithIgnoreCase(name);
//		    } else if (department != null) {
//		        return doctorRepository.findByDepartmentStartingWithIgnoreCase(department);
//		    } else {
//		        return doctorRepository.findAll();
//		    }
//		}



	public List<Doctor> getDoctorsByDepartment(String departmentName) {
        return doctorRepository.findByDepartment(departmentName);
    }
	
	

	public List<Search> searchEntities(String query) {
	    List<Doctor> doctors = doctorRepository.findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(query, query);
	    List<Clinic> clinics = clinicRepository.findByNameContainingIgnoreCase(query);
	   
	    List<Search> results = new ArrayList<>();
	    doctors.forEach(doc -> results.add(new Search(doc.getId(), doc.getName(), "Doctor", doc.getDepartment())));
	    clinics.forEach(clinic -> results.add(new Search(clinic.getId(), clinic.getName(), "Clinic", clinic.getAddress())));
	   // labs.forEach(lab -> results.add(new SearchResult(lab.getId(), lab.getName(), "Lab", lab.getLocation())));

	    return results;
	}
	
	public DoctorAvailability addAvailability(Long doctorId, LocalDate date, List<String> slots) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Check if availability for this date already exists
        Optional<DoctorAvailability> existingAvailability = doctorAvailabilityRepository.findByDoctorIdAndAvailableDate(doctorId, date);

        if (existingAvailability.isPresent()) {
            throw new RuntimeException("Availability for this date already exists. Update instead.");
        }

        DoctorAvailability availability = new DoctorAvailability();
        availability.setDoctor(doctor);
        availability.setAvailableDate(date);
        availability.setAvailableSlots(slots);

        return doctorAvailabilityRepository.save(availability);
    }

//	public List<String> getAvailableSlots(Long doctorId, LocalDate date) {
//		// TODO Auto-generated method stub
//		return doctorAvailabilityRepository.findAll();
//	}


	
	//time slot
//		public List<String> getAvailableSlots(Long doctorId) {
//	        Doctor doctor = doctorRepository.findById(doctorId)
//	        		.orElseThrow(() -> new RuntimeException("Doctor not found"));
//	        return doctor.getAvailableSlots(); 
//	    }

	
//	public Doctor addDoctor(Doctor doctor, Long clinicId) {
//    Clinic clinic = clinicRepository.findById(clinicId)
//        .orElseThrow(() -> new RuntimeException("Clinic not found"));
//
//    doctor.setClinic(clinic);
//    doctor.setReviewCount(0); //when a review is added it count changes
//    
//    return doctorRepository.save(doctor);
//}
//
//public Doctor addDoctor(Doctor doctor) {
//    if (doctor.getClinic() == null || doctor.getClinic().getId() == null) {
//        throw new IllegalArgumentException("Clinic ID is required.");
//    }
//
//    // Fetch clinic details
//    Clinic clinic = clinicRepository.findById(doctor.getClinic().getId())
//            .orElseThrow(() -> new ClinicNotFoundException("Clinic not found with ID: " + doctor.getClinic().getId()));
//
//    // Set required fields
//    doctor.setClinic(clinic);
//    doctor.setReviewCount(0);
//    doctor.setRating(0.0);  
//
//    // Save doctor first to generate an ID
//    Doctor savedDoctor = doctorRepository.save(doctor);
//
//    // Save availability if provided
//    if (doctor.getAvailabilities() != null && !doctor.getAvailabilities().isEmpty()) {
//        for (DoctorAvailability availability : doctor.getAvailabilities()) {
//            availability.setDoctor(savedDoctor);  // Associate availability with the doctor
//            doctorAvailabilityRepository.save(availability);
//        }
//    }
//
//    return savedDoctor;
//}

}
