package com.medo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medo.entity.Doctor;
import com.medo.pojo.response.ApiResponse;
import com.medo.service.DoctorService;


@RestController
@RequestMapping(value="/auth/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	// adddoctor
		@PostMapping("/add")
		public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
			Doctor savedDoctor = doctorService.addDoctor(doctor);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
		}
		
		// Get all doctors
	    @GetMapping("/alldoctors")
	    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
	        List<Doctor> listOfDoctors = doctorService.getAllDoctors();
	        ApiResponse<List<Doctor>> response = new ApiResponse<>("success", listOfDoctors);
	        return ResponseEntity.ok(response);
	    }

	    
	    @GetMapping("/basicdetails/{doctorId}")
	    public ResponseEntity<Doctor>geyDoctorById(@PathVariable Long doctorId){
	    	return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
	    }
	    
	    // Search doctor by name
	    @GetMapping("/search")
	    public ResponseEntity<ApiResponse<List<Doctor>>> searchDoctorByName(@RequestParam String name) {
	        List<Doctor> doctors = doctorService.searchDoctorByName(name);
	        ApiResponse<List<Doctor>> response = new ApiResponse<>("success", doctors);
	        return ResponseEntity.ok(response);
	    }
	 // avilable timeslot-unnessary
		@GetMapping("/{doctorId}/slots")
		public ResponseEntity<List<String>> getAvailableSlots(@PathVariable Long doctorId) {
			return ResponseEntity.ok(doctorService.getAvailableSlots(doctorId));
		}
}
