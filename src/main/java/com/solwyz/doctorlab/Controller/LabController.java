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

import com.solwyz.doctorlab.Entity.Laboratory;
import com.solwyz.doctorlab.Service.LabService;
import com.solwyz.doctorlab.pojo.response.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/lab")
@Tag(name = "Lab Authentication", description = "APIs for Lab related operations")
public class LabController {
	
	

	@Autowired
	private LabService labService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createLab(@RequestBody Laboratory lab) {
		return ResponseEntity.ok(labService.createLab(lab));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateLab(@PathVariable Long id, @RequestBody Laboratory lab) {
		return ResponseEntity.ok(labService.updateLab(id, lab));
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse<List<Laboratory>>> getAllLabs() {
		List<Laboratory> labs = labService.getAllLabs();
		ApiResponse<List<Laboratory>> response = new ApiResponse<>("success", labs);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLabById(@PathVariable Long id) {
		return ResponseEntity.ok(labService.getLabById(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteLab(@PathVariable Long id) {
		labService.deleteLab(id);
		return ResponseEntity.ok("Lab deleted successfully");
	}


	@PostMapping("/rate/{id}")
	public ResponseEntity<?> rateLab(@PathVariable Long id, @RequestParam double ratings) {
		return ResponseEntity.ok(labService.rateLab(id, ratings));
	}
	
	@GetMapping("/rating/{id}")
	public ResponseEntity<?> getLabRating(@PathVariable Long id) {
		return ResponseEntity.ok(labService.getLabRating(id));
	}

	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<Laboratory>>> searchLabs(@RequestParam(required = false) String name,
			@RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating) {
		List<Laboratory> labs= labService.searchLabs(name, minRating, maxRating);
		ApiResponse<List<Laboratory>> response = new ApiResponse<>("success", labs);
		return ResponseEntity.ok(response);
	}


}
