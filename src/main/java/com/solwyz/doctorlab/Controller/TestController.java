package com.solwyz.doctorlab.Controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.solwyz.doctorlab.Entity.Test;
import com.solwyz.doctorlab.Repo.TestRepository;
import com.solwyz.doctorlab.Service.TestService;
import com.solwyz.doctorlab.pojo.response.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/test")
@Tag(name = "Lab Test Authentication", description = "APIs for Lab Test related operations")
public class TestController {

	@Autowired
	private TestService testService;
	
	@Autowired
	private TestRepository testRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	 @PostMapping("/create")
	    public ResponseEntity<?> createTest(@RequestBody Test test) {
	        logger.info("Received request to create test: {}", test);
	        Test savedTest = testService.createTest(test);
	        logger.info("Test created successfully: {}", savedTest);
	        return ResponseEntity.ok(savedTest);
	    }
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTest(@PathVariable Long id, @RequestBody Test test) {
	    Test updatedTest = testService.updateTest(id, test);
	    return ResponseEntity.ok(updatedTest);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse<List<Test>>> getAllTests() {
	    List<Test> tests = testService.getAllTests();
	    ApiResponse<List<Test>> response = new ApiResponse<>("success", tests);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTestById(@PathVariable Long id) {
		return ResponseEntity.ok(testService.getTestById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTest(@PathVariable Long id) {
		testService.deleteTest(id);
		return ResponseEntity.ok("Test deleted successfully");
	}
	
	@GetMapping("sort/all")
	public ResponseEntity<ApiResponse<List<Test>>> getTestsByCategory( @RequestParam(required = false) Long categoryId) {
	    
	    List<Test> tests;
	    
	    if (categoryId != null) {
	        tests = testService.getTestsByCategory(categoryId);
	    } else {
	        tests = testService.getAllTests();
	    }   
	    ApiResponse<List<Test>> response = new ApiResponse<>("success", tests);
	    return ResponseEntity.ok(response);
	}

	@PostMapping("/calculate-total")
	public ResponseEntity<?> calculateTotalAmount(@RequestParam Long testId, @RequestParam int patientCount) {
	    Test test = testRepository.findById(testId)
	            .orElseThrow(() -> new EntityNotFoundException("Test with ID " + testId + " not found"));

	    test.setPatientCount(patientCount);
	    test.setTotalAmount((test.getAmount() + test.getSampleCollectionCharge()) * patientCount);

	    testRepository.save(test);
	    return ResponseEntity.ok(test);
	}

	

}
