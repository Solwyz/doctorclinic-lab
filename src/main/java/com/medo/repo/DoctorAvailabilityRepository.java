package com.medo.repo;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medo.entity.Doctor;
import com.medo.entity.DoctorAvailability;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

	//Optional<Doctor> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate date);
	
	Optional<DoctorAvailability> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);


}
