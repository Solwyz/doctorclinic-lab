package com.medo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medo.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findAll();

	

	List<Doctor> findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(String query, String query2);



	List<Doctor> findByDepartment(String departmentName);

}
