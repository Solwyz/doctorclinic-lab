package com.solwyz.doctorlab.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.Department;
import com.solwyz.doctorlab.Repo.DepartmentRepo;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepo departmentRepository;

	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	public Optional<Department> getDepartmentById(Long id) {
		return departmentRepository.findById(id);
	}

	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department updateDepartment(Long id, Department departmentDetails) {
		return departmentRepository.findById(id).map(department -> {
			department.setDepartmentName(departmentDetails.getDepartmentName());
			return departmentRepository.save(department);
		}).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
	}

	public void deleteDepartment(Long id) {
		departmentRepository.deleteById(id);
	}

}
