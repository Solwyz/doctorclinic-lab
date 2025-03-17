package com.solwyz.doctorlab.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solwyz.doctorlab.Entity.Patient;
import com.solwyz.doctorlab.Service.PatientService;

import io.swagger.v3.oas.annotations.tags.Tag;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/patient")
@Tag(name = "patient Authentication", description = "APIs for patient related operations")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping("/create")
	public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
		return ResponseEntity.ok(patientService.createPatient(patient));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
		return ResponseEntity.ok(patientService.updatePatient(id, patient));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		return ResponseEntity.ok("Patient deleted successfully");
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable Long id) {
		return ResponseEntity.ok(patientService.getPatientById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatients() {
		return ResponseEntity.ok(patientService.getAllPatients());
	}

}
