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

import com.solwyz.doctorlab.Entity.Test;
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
	
	
	@PostMapping("/create")
	public ResponseEntity<?>createTest(@RequestBody Test  test){
		return ResponseEntity.ok(testService.createTest(test));
		
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
	
}
