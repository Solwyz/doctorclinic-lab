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

import com.solwyz.doctorlab.Entity.Doctor;
import com.solwyz.doctorlab.Service.DoctorService;
import com.solwyz.doctorlab.pojo.response.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctor")
@Tag(name = "doctor Authentication", description = "APIs for Doctor related operations")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/create")
	public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) {
		return ResponseEntity.ok(doctorService.createDoctor(doctor));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
		return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
		return ResponseEntity.ok(doctorService.getDoctorById(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
		doctorService.deleteDoctor(id);
		return ResponseEntity.ok("Doctor deleted successfully");
	}

	@GetMapping("/clinic/{clinicId}")
	public ResponseEntity<ApiResponse<List<Doctor>>> getDoctorsByClinic(@PathVariable Long clinicId) {
		List<Doctor> doctors = doctorService.getDoctorsByClinic(clinicId);
		ApiResponse<List<Doctor>> response = new ApiResponse<>("success", doctors);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/department/{department}")
	public ResponseEntity<ApiResponse<List<Doctor>>> getDoctorsByDepartment(@PathVariable String department) {
		List<Doctor> doctors = doctorService.getDoctorsByDepartment(department);
		ApiResponse<List<Doctor>> response = new ApiResponse<>("success", doctors);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<Doctor>>>searchDoctors(@RequestParam(required = false) String name,
			@RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating,
			@RequestParam(required = false) String availabilityTimes) {
		List<Doctor>doctors=doctorService.searchDoctors(name, minRating, maxRating, availabilityTimes);
		ApiResponse<List<Doctor>>response=new ApiResponse<>("success",doctors);
		return ResponseEntity.ok(response);
	}
	
	  @GetMapping("/all")
	    public ResponseEntity<List<Doctor>> getAllDoctors() {
	        return ResponseEntity.ok(doctorService.getAllDoctors());
	    }
}
