package com.solwyz.doctorlab.Controller;

import java.util.List;
import java.util.Optional;

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

import com.solwyz.doctorlab.Entity.Department;
import com.solwyz.doctorlab.Service.DepartmentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/department")
@Tag(name = "department Authentication", description = "APIs for department related operations")
public class DepartmentController {
	
	
	@Autowired
    private DepartmentService departmentService;

    // Get all departments
    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    // Get department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Department>> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    // Create a new department
    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    // Update department by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, department));
    }

    // Delete department by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully.");
    }

}
