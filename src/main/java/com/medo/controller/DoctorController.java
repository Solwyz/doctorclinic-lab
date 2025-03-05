package com.medo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value="/auth/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	// addoctor
	@PostMapping("/doctors")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor, @RequestParam Long clinicId) {
	    Doctor savedDoctor = doctorService.addDoctor(doctor, clinicId);
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
	    

//	    @GetMapping("/search")
//	    public ResponseEntity<ApiResponse<List<Doctor>>> searchDoctors(
//	            @RequestParam(required = false) String name,
//	            @RequestParam(required = false) String department) {
//
//	        List<Doctor> doctors = doctorService.searchDoctors(name, department);
//	        ApiResponse<List<Doctor>> response = new ApiResponse<>("success", doctors);
//	        return ResponseEntity.ok(response);
//	    }

	    
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
}
