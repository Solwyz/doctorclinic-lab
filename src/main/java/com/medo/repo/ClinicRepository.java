package com.medo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medo.entity.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

	Clinic save(Clinic clinic);
	 List<Clinic> findAll();

}
