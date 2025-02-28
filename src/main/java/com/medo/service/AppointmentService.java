package com.medo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.medco.Enum.AppointmentStatus;
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
		Patient patient = patientRepository.findById(patientId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found: " + patientId));

		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found: " + doctorId));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime dateTime;
		try {
			dateTime = LocalDateTime.parse(appointmentDateTime, formatter);
		} catch (DateTimeParseException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Use yyyy-MM-dd ");
		}

		boolean exists = appointmentRepository.existsByDoctorAndAppointmentDate(doctor, dateTime);
		if (exists) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Doctor is already booked at this time");
		}

		Appointment appointment = new Appointment();
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		appointment.setAppointmentDate(dateTime);
		appointment.setStatus(AppointmentStatus.BOOKED);

		return appointmentRepository.save(appointment);
	}

//	public Appointment bookAppointment(Long patientId, Long doctorId, String appointmentDateTime) {
//		Patient patient = patientRepository.findById(patientId)
//				.orElseThrow(() -> new RuntimeException("Patient not found"));
//		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
//
//		Appointment appointment = new Appointment();
//		appointment.setPatient(patient);
//		appointment.setDoctor(doctor);
//		appointment.setAppointmentDate(appointmentDateTime);
//		appointment.setStatus(AppointmentStatus.valueOf("BOOKED")); 
//
//
//		return appointmentRepository.save(appointment);
//	}
//	

	// reshedule appoitment
	public void rescheduleAppointment(Long appointmentId, String newDateTime) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		// Convert String to LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime;
		try {
			dateTime = LocalDateTime.parse(newDateTime, formatter);
		} catch (DateTimeParseException e) {
			throw new RuntimeException("Invalid date format. Use yyyy-MM-dd HH:mm");
		}

		appointment.setAppointmentDate(dateTime);
		appointment.setStatus(AppointmentStatus.BOOKED);

		appointmentRepository.save(appointment);
	}

	// completed
	public void completedAppointment(Long appointmentId) {

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		appointment.setStatus(AppointmentStatus.valueOf("COMPLETED"));
		//appointment.setStatus(AppointmentStatus.COMPLETED);
		appointmentRepository.save(appointment);

	}
	
	public void cancelAppointment(Long appointmentId, Long patientId) {
	    Appointment appointment = appointmentRepository.findById(appointmentId)
	            .orElseThrow(() -> new RuntimeException("Appointment not found"));

	    if (!appointment.getPatient().getId().equals(patientId)) {
	        throw new RuntimeException("This appointment does not belong to the given patient");
	    }

	    appointment.setStatus(AppointmentStatus.CANCELLED);
	    appointmentRepository.save(appointment);
	}



//appointment history -upcoming completed cancelled

	public List<Appointment> getCompletedAppointments(Long patientId) {
		return appointmentRepository.findByPatientIdAndStatus(patientId, AppointmentStatus.COMPLETED);
	}

	
	// api to cancell appoitment.that list must be get from getCancelled
	// Appoitments.

	public List<Appointment> getCancelledAppointments(Long patientId) {
	    return appointmentRepository.findByPatientIdAndStatus(patientId, AppointmentStatus.CANCELLED);
	}

	

	public List<Appointment> getUpcomingAppointments(Long patientId) {
		return appointmentRepository.findByPatientIdAndStatus(patientId, AppointmentStatus.BOOKED);
	}
	

	public void submitFeedback(Long appointmentId, Integer rating, String feedback) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
			throw new RuntimeException("Feedback can only be given for completed appointments");
		}

		appointment.setRating(rating);
		appointment.setFeedback(feedback);

		appointmentRepository.save(appointment);
	}

	
//			 public List<Doctor> getCancelledAppointments(Long patientId) {
//	        List<Appointment> cancelledAppointments = appointmentRepository.findByPatientIdAndStatus(patientId, "CANCELLED");
//
//	        List<Doctor> cancelledDoctors = new ArrayList<>();
//	        for (Appointment appointment : cancelledAppointments) {
//	            cancelledDoctors.add(appointment.getDoctor());
//	        }
//	        return cancelledDoctors;
//	    }

//		    public List<Doctor> getUpcomingAppointments(Long patientId) {
//	        List<Appointment> upcomingAppointments = appointmentRepository.findByPatientIdAndStatus(patientId, "UPCOMING");
//
//	        List<Doctor> upcomingDoctors = new ArrayList<>();
//	        for (Appointment appointment : upcomingAppointments) {
//	            upcomingDoctors.add(appointment.getDoctor());
//	        }
//	        return upcomingDoctors;
//	    }

	// public List<Doctor> getCompletedAppointments(Long patientId) {
//			        List<Appointment> completedAppointments = appointmentRepository.findByPatientIdAndStatus(patientId, "COMPLETED");
	//
//			        List<Doctor> completedDoctors = new ArrayList<>();
//			        for (Appointment appointment : completedAppointments) {
//			            completedDoctors.add(appointment.getDoctor());
//			        }
//			        return completedDoctors;
//			    }
//			 
//			 

}
