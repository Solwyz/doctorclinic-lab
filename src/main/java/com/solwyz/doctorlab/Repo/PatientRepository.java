package com.solwyz.doctorlab.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solwyz.doctorlab.Entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	List<Patient> findByUserId(Long userId);

}
