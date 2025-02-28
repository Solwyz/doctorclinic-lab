package com.medo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medo.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findAll();

	List<Doctor> findByNameContainingIgnoreCase(String name);

	List<Doctor> findByNameStartingWithIgnoreCaseAndDepartmentStartingWithIgnoreCase(String name, String department);

	List<Doctor> findByNameStartingWithIgnoreCase(String name);

	List<Doctor> findByDepartmentStartingWithIgnoreCase(String department);

	List<Doctor> findByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(String name, String department);

	List<Doctor> findByDepartmentContainingIgnoreCase(String department);

	List<Doctor> findByDepartment(String departmentName);

}
