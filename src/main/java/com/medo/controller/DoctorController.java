package com.medo.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medo.entity.Doctor;

import com.medo.entity.Search;
import com.medo.pojo.response.ApiResponse;
import com.medo.service.DoctorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	// addoctor
	 private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
	 

	    @PostMapping
	    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
	        Doctor savedDoctor = doctorService.addDoctor(doctor);
	        return ResponseEntity.ok(savedDoctor);
	    }



		// Get all doctors
	    @GetMapping("/alldoctors")
	    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
	        List<Doctor> listOfDoctors = doctorService.getAllDoctors();
	        ApiResponse<List<Doctor>> response = new ApiResponse<>("success", listOfDoctors);
	        return ResponseEntity.ok(response);
	    }

	    
	    @GetMapping("/doctordetails/{doctorId}")
	    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) {
	        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
	    }
	    
	    // Search doctor 
	    @GetMapping("/search")
	    public ResponseEntity<ApiResponse<List<Search>>> searchEntities(@RequestParam String query) {
	        List<Search> results = doctorService.searchEntities(query);
	        return ResponseEntity.ok(new ApiResponse<>("success", results));
	    }
	    


	    
	    //all departments
	    @GetMapping("/doctors/{departmentName}")
	    public ResponseEntity<List<Doctor>> getDoctorsByDepartment(@PathVariable String departmentName) {
	        List<Doctor> doctors = doctorService.getDoctorsByDepartment(departmentName);
	        return ResponseEntity.ok(doctors);
	    }
	    
	 // avilable timeslot
//		@GetMapping("/{doctorId}/slots")
//		public ResponseEntity<List<String>> getAvailableSlots(@PathVariable Long doctorId) {
//			return ResponseEntity.ok(doctorService.getAvailableSlots(doctorId));
//		}
	    
//		@PostMapping("/doctors")
//		public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor, @RequestParam Long clinicId) {
//		    Doctor savedDoctor = doctorService.addDoctor(doctor, clinicId);
//		    return ResponseEntity.ok(savedDoctor);
//		}
//		@PostMapping("/doctors")
//		public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
//		    Doctor savedDoctor = doctorService.addDoctor(doctor);
//		    return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
//		}
//	    @GetMapping("/search")
//	    public ResponseEntity<ApiResponse<List<Doctor>>> searchDoctors(
//	            @RequestParam(required = false) String name,
//	            @RequestParam(required = false) String department) {
//
//	        List<Doctor> doctors = doctorService.searchDoctors(name, department);
//	        ApiResponse<List<Doctor>> response = new ApiResponse<>("success", doctors);
//	        return ResponseEntity.ok(response);
//	    }
	    
		 // availability for a doctor
//	    @PostMapping("/{doctorId}/availability")
//	    public ResponseEntity<DoctorAvailability> addAvailability(
//	            @PathVariable Long doctorId,
//	            @RequestParam LocalDate date,
//	            @RequestBody List<String> slots) {
//	        
//	        DoctorAvailability availability = doctorService.addAvailability(doctorId, date, slots);
//	        return ResponseEntity.ok(availability);
//	    }
	    
//	    @GetMapping("/{doctorId}/availability")
//	    public ResponseEntity<List<String>> getAvailableSlots(
//	            @PathVariable Long doctorId,
//	            @RequestParam LocalDate date) {
//	        
//	        List<String> slots = doctorService.getAvailableSlots(doctorId, date);
//	        return ResponseEntity.ok(slots);
//	    }
	// 
//		 @PostMapping("/doctors")
//	    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
//	        Doctor savedDoctor = doctorService.addDoctor(doctor);
//	        return ResponseEntity.ok(savedDoctor);
//	    }
//	 @PostMapping("/adddoctor")
//	    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
//	        logger.info("Received request to add doctor: {}", doctor.getName());
//
//	        try {
//	            Doctor savedDoctor = doctorService.addDoctor(doctor);
//	            logger.info("Doctor added successfully with ID: {}", savedDoctor.getId());
//	            return ResponseEntity.ok(savedDoctor);
//	        } catch (Exception e) {
//	            logger.error("Error while adding doctor: {}", e.getMessage(), e);
//	            return ResponseEntity.status(500).body(null);
//	        }
//	    }
//	


}
