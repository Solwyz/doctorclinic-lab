package com.medo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medo.entity.Patient;
import com.medo.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    

	List<Report> findByPatientOrderByCreatedDateDesc(Patient patient);
}

