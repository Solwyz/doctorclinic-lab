package com.medo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.medo.entity.Appointment;
import com.medo.entity.Doctor;
import com.medo.entity.Patient;
import com.medo.repo.AppointmentRepository;
import com.medo.repo.DoctorRepository;
import com.medo.repo.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	// book now
	public Appointment bookAppointment(Long patientId, Long doctorId, String appointmentDateTime) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Patient not found"));
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));

		Appointment appointment = new Appointment();
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		appointment.setAppointmentDate(appointmentDateTime);
		appointment.setStatus("BOOKED");

		return appointmentRepository.save(appointment);
	}
	
	 public void addAvailableSlots(Long doctorId, List<String> slots) {
	        Doctor doctor = doctorRepository.findById(doctorId)
	                .orElseThrow(() -> new RuntimeException("Doctor not found"));

	        doctor.setAvailableSlots(slots); 
	        doctorRepository.save(doctor);
	    }
	
	//time slot
		public List<String> getAvailableSlots(Long doctorId) {
	        Doctor doctor = doctorRepository.findById(doctorId)
	        		.orElseThrow(() -> new RuntimeException("Doctor not found"));
	        return doctor.getAvailableSlots(); 
	    }
	
	//reshedule appoitment
	
		
		
		//api to cancell appoitment.that list must be get from getCancelled Appoitments.
		
		//completed 
		public void completedAppointment(Long appointmentId) {
			
			Appointment appointment=appointmentRepository.findById(appointmentId)
					.orElseThrow(()->new RuntimeException("Appointment not found"));
			
			appointment.setStatus("COMPLETED");
			appointmentRepository.save(appointment);
			
		}

//appointment history -upcoming completed cancelled
	public List<Doctor> getCancelledAppointment(Long patientId) {
		List<Appointment> cancelledAppointments = appointmentRepository.findByPatientIdAndStatus(patientId,
				"CANCELLED");

		List<Doctor> cancelledDoctors = new ArrayList<>();
		for (Appointment appointment : cancelledAppointments) {
			cancelledDoctors.add(appointment.getDoctor());
		}
		return cancelledDoctors;
	}

	public List<Doctor> getUpcomingAppointments(Long patientId) {
		List<Appointment> upcomingAppointments = appointmentRepository.findByPatientIdAndStatus(patientId, "UPCOMING");

		List<Doctor> upcomingDoctors = new ArrayList<>();
		for (Appointment appointment : upcomingAppointments) {
			upcomingDoctors.add(appointment.getDoctor());
		}
		return upcomingDoctors;
	}

	public List<Doctor> getCompletedAppointments(Long patientId) {
		List<Appointment> completedAppointments = appointmentRepository.findByPatientIdAndStatus(patientId,
				"COMPLETED");

		List<Doctor> completedDoctors = new ArrayList<>();
		for (Appointment appointment : completedAppointments) {
			completedDoctors.add(appointment.getDoctor());
		}
		return completedDoctors;
	}

	

}
