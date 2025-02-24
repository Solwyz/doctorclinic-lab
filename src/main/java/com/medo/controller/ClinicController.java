package com.medo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medo.entity.Clinic;
import com.medo.pojo.response.ApiResponse;
import com.medo.service.ClinicService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping(value="/auth/clinic")
public class ClinicController {
	
	@Autowired
	private ClinicService clinicService;
	
	@PostMapping("/addClinic")
	public ResponseEntity<Clinic>addClinic(@RequestBody Clinic clinic){
		Clinic clinicadded=clinicService.addClinic(clinic);
		return ResponseEntity.ok(clinic);
	}
	
	@GetMapping("/getallClinic")
	public ResponseEntity<ApiResponse<List<Clinic>>> getAllClinic() {
	    List<Clinic> clinics = clinicService.getAllClinics();
	    ApiResponse<List<Clinic>> response = new ApiResponse<>("success", clinics);
	    return ResponseEntity.ok(response);
	}

	//check doctors -list of doctors in that clinic
	//list avilable doctors

}
