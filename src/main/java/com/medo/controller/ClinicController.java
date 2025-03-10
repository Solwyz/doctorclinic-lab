package com.medo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medo.entity.Clinic;
import com.medo.entity.Report;
import com.medo.pojo.response.ApiResponse;
import com.medo.service.ClinicService;




@RestController
@RequestMapping(value="/auth/clinic")
public class ClinicController {
	
	@Autowired
	private ClinicService clinicService;
	

	private static final Logger logger = LoggerFactory.getLogger(ClinicController.class);


	
	@PostMapping("/addClinic")
	public ResponseEntity<Clinic>addClinic(@RequestBody Clinic clinic){
		Clinic clinicadded=clinicService.addClinic(clinic);
		return ResponseEntity.ok(clinicadded);
	}
			
	
	
	@GetMapping("/getallClinic")
	public ResponseEntity<ApiResponse<List<Clinic>>> getAllClinic() {
	    List<Clinic> clinics = clinicService.getAllClinics();
	    ApiResponse<List<Clinic>> response = new ApiResponse<>("success", clinics);
	    return ResponseEntity.ok(response);
	}

	//check doctors -list of doctors in that clinic
	//list avilable doctors

	//reports
	@GetMapping("/reports/{id}")
	public ResponseEntity<List<Report>>getReports(@PathVariable Long id){
		List<Report>reports=clinicService.getPatientReports(id);
		return ResponseEntity.ok(reports);
	}
	
//	@PostMapping("/addClinic")
//  public ResponseEntity<Clinic> addClinic(@RequestBody Clinic clinic) {
//      logger.info("Received request to add clinic: {}", clinic);
//
//      Clinic clinicAdded = clinicService.addClinic(clinic);
//
////      if (clinicAdded == null) {
////          logger.error("Clinic was not added properly!");
////          return ResponseEntity.badRequest().build();
////      }
//
//      logger.info("Successfully added clinic with ID: {}", clinicAdded.getId());
//      return ResponseEntity.ok(clinicAdded);
//  }
//	
	
}
