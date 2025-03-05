package com.medo.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medco.Enum.AppointmentStatus;
import com.medo.entity.Appointment;
import com.medo.entity.Doctor;
import com.medo.entity.Patient;



public interface AppointmentRepository extends JpaRepository<Appointment,Long>{


	List<Appointment> findByPatientIdAndStatus(Long patientId, String status);
	
	List<Appointment> findByStatus(AppointmentStatus status);
	 
	

	boolean existsByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime dateTime);

	List<Appointment> findByPatientIdAndStatus(Long patientId, AppointmentStatus cancelled);

	boolean existsByDoctorAndAppointmentDateAndSlot(Doctor doctor, LocalDate availableDate, String availableSlot);

	List<Appointment> findByUserIdAndStatus(Long userId, AppointmentStatus booked);

	

}
