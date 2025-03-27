package com.solwyz.doctorlab.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solwyz.doctorlab.Entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
