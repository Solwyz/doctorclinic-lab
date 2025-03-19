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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solwyz.doctorlab.Entity.Clinic;
import com.solwyz.doctorlab.Service.ClinicService;
import com.solwyz.doctorlab.pojo.response.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clinic")
@Tag(name = "clinic Authentication", description = "APIs for Clinic related operations")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;

	@PostMapping("/create")
	public ResponseEntity<?> createClinic(@RequestBody Clinic clinic) {
		return ResponseEntity.ok(clinicService.createClinic(clinic));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateClinic(@PathVariable Long id, @RequestBody Clinic clinic) {
		return ResponseEntity.ok(clinicService.updateClinic(id, clinic));
	}

	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse<List<Clinic>>> getAllClinics() {
	    List<Clinic> clinics = clinicService.getAllClinics();
	    ApiResponse<List<Clinic>> response = new ApiResponse<>( "success", clinics);
	    return ResponseEntity.ok(response);
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> getClinicById(@PathVariable Long id) {
		return ResponseEntity.ok(clinicService.getClinicById(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteClinic(@PathVariable Long id) {
		clinicService.deleteClinic(id);
		return ResponseEntity.ok("Clinic deleted successfully");
	}

	@PostMapping("/rate/{id}")
	public ResponseEntity<?> rateClinic(@PathVariable Long id, @RequestParam double rating) {
		return ResponseEntity.ok(clinicService.rateClinic(id, rating));
	}

	@GetMapping("/rating/{id}")
	public ResponseEntity<?> getClinicRating(@PathVariable Long id) {
		return ResponseEntity.ok(clinicService.getClinicRating(id));
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<Clinic>>>searchClinics(@RequestParam(required = false) String name,
			@RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating) {
		List<Clinic> clinics=clinicService.searchClinics(name, minRating, maxRating);
		ApiResponse<List<Clinic>>response =new ApiResponse<>("success",clinics);
		return ResponseEntity.ok(response);
	}

}
