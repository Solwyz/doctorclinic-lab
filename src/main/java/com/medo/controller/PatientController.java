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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medo.entity.Doctor;
import com.medo.entity.Patient;
import com.medo.pojo.response.ApiResponse;
import com.medo.service.PatientService;

@RestController
@RequestMapping("/api/home")
public class PatientController {

//    private final PatientService patientService;

	@Autowired
	PatientService patientService;

	//allworking
	
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

	// adddoctor
	@PostMapping("/add")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		Doctor savedDoctor = patientService.addDoctor(doctor);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
	}

	// Get all doctors
    @GetMapping("/alldoctors")
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
        List<Doctor> listOfDoctors = patientService.getAllDoctors();
        ApiResponse<List<Doctor>> response = new ApiResponse<>("success", listOfDoctors);
        return ResponseEntity.ok(response);
    }

    
    // Search doctor by name
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Doctor>>> searchDoctorByName(@RequestParam String name) {
        List<Doctor> doctors = patientService.searchDoctorByName(name);
        ApiResponse<List<Doctor>> response = new ApiResponse<>("success", doctors);
        return ResponseEntity.ok(response);
    }
}
