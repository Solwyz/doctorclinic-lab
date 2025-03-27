package com.solwyz.doctorlab.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.solwyz.doctorlab.Entity.CheckUpCategory;
import com.solwyz.doctorlab.Entity.Clinic;
import com.solwyz.doctorlab.Service.CheckUpService;
import com.solwyz.doctorlab.pojo.response.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/checkUp")
@Tag(name = "checkUp Authentication", description = "APIs for AllCheckup related operations")
public class CheckUpController {
	
	@Autowired
	private CheckUpService checkUpService;
	
	
	@PostMapping("/create")
    public ResponseEntity<CheckUpCategory> createCheckUpCategory(@RequestBody CheckUpCategory category) {
        CheckUpCategory savedCategory = checkUpService.createCheckUpCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CheckUpCategory category) {
		return ResponseEntity.ok(checkUpService.updateCategory(id, category));
	}

	@GetMapping("/all")
	public ResponseEntity<ApiResponse<List<CheckUpCategory>>> getAllCategory() {
		List<CheckUpCategory> checkup = checkUpService.getAllCategory();
		ApiResponse<List<CheckUpCategory>> response = new ApiResponse<>("success", checkup);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
		return ResponseEntity.ok(checkUpService.getCategoryById(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		checkUpService.deleteCategory(id);
		return ResponseEntity.ok("Category deleted successfully");
	}

}
