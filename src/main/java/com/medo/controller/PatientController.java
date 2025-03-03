package com.medo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medo.entity.Doctor;
import com.medo.entity.Notification;
import com.medo.entity.Patient;
import com.medo.pojo.response.ApiResponse;
import com.medo.service.PatientService;

@RestController
@RequestMapping("/api/home")
public class PatientController {

//    private final PatientService patientService;

	@Autowired
	PatientService patientService;

	
	
	@PostMapping(value = "/addpatient")
	public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(patient));
	}


	@GetMapping("/getall")
	public ResponseEntity<ApiResponse<List<Patient>>> getAllPatients() {
	    List<Patient> patients = patientService.getAllPatients();
	    ApiResponse<List<Patient>> response = new ApiResponse<>("success", patients);
	    return ResponseEntity.ok(response);
	}


	//  patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        ApiResponse<Patient> response = new ApiResponse<>("success", patient);
        return ResponseEntity.ok(response);
    }

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
		boolean patientdeleted = patientService.deletePatient(id);
		return new ResponseEntity<Patient>(HttpStatus.OK);
	}

	// edit patient details
	@PutMapping(value = "/editdetails/{id}")
	public ResponseEntity<Patient> editPatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
		Patient editpatient = patientService.editPatient(id, updatedPatient);
		return ResponseEntity.ok(editpatient);

	}
	
//getActivity
//	 @GetMapping("/{userId}")
//	    public ResponseEntity<List<String>> getUserActivity(@PathVariable Long Id) {
//	        return ResponseEntity.ok(patientService.getUserActivity(userId));
//	    }
	

	//notification
	@GetMapping("/notification/{id}")
	public ResponseEntity<List<Notification>> getNotification(@PathVariable Long id){
		List<Notification>notifications=patientService.getNotification(id);
		return ResponseEntity.ok(notifications);
	}
	
}

	

