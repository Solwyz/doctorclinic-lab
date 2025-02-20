package com.medo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medo.entity.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor,Long> {

	

	List<Doctor> findAll();

}
