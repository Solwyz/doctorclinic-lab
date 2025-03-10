package com.medo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.medco.Enum.AppointmentStatus;
import com.medco.dto.AppointmentRequest;
import com.medo.entity.Appointment;
import com.medo.entity.Doctor;

import com.medo.entity.Patient;
import com.medo.repo.AppointmentRepository;

import com.medo.repo.DoctorRepository;
import com.medo.repo.PatientRepository;

@Transactional
@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;
	
//	@Autowired
//	private DoctorAvailabilityRepository doctorAvailabilityRepository;

	
	@Transactional
	public Appointment bookAppointment(AppointmentRequest request) {
	   
	    Doctor doctor = doctorRepository.findById(request.getDoctorId())
	            .orElseThrow(() -> new RuntimeException("Doctor not found"));

	    Patient patient = patientRepository.findById(request.getPatientId())
	            .orElseThrow(() -> new RuntimeException("Patient not found"));

	    if (!doctor.getAvailableDate().equals(request.getAvailableDate())) {
	        throw new RuntimeException("Doctor is not available on this date.");
	    }

	    // slot is already booked
	    boolean exists = appointmentRepository.existsByDoctorAndAppointmentDateAndSlot(
	            doctor, request.getAvailableDate(), request.getAvailableSlot());

	    if (exists) {
	        throw new RuntimeException("Selected slot is already booked.");
	    }

	    // Remove booked slot from doctor's available slots
	    List<String> slots = doctor.getAvailableSlots();
	    if (!slots.remove(request.getAvailableSlot())) {
	        throw new RuntimeException("Slot not available for the selected date.");
	    }
	    doctor.setAvailableSlots(slots);
	    doctorRepository.save(doctor);

	    // Save appointment with "BOOKED" status
	    Appointment appointment = new Appointment();
	    appointment.setDoctor(doctor);
	    appointment.setPatient(patient);
	    appointment.setUserId(request.getUserId());
	    appointment.setAppointmentDate(request.getAvailableDate());
	    appointment.setSlot(request.getAvailableSlot());
	    appointment.setStatus(AppointmentStatus.BOOKED);

	    return appointmentRepository.save(appointment);
	}


	// completed
	public void completedAppointment(Long appointmentId) {

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		appointment.setStatus(AppointmentStatus.valueOf("COMPLETED"));
		// appointment.setStatus(AppointmentStatus.COMPLETED);
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

	public List<Appointment> getCompletedAppointments(Long userId) {
	    return appointmentRepository.findByUserIdAndStatus(userId, AppointmentStatus.COMPLETED);
	}


//	public List<Appointment> getCompletedAppointments(Long patientId) {
//		return appointmentRepository.findByPatientIdAndStatus(patientId, "COMPLETED");
//	} 
//	
	// api to cancell appoitment.that list must be get from getCancelled
	// Appoitments.

	public List<Appointment> getCancelledAppointments(Long userId) {
	    return appointmentRepository.findByUserIdAndStatus(userId, AppointmentStatus.CANCELLED);
	}


	
	public List<Appointment> getUpcomingAppointments(Long userId) {
		return appointmentRepository.findByUserIdAndStatus(userId, AppointmentStatus.BOOKED);
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


//	public Appointment confirmBooking(Long userId, Long patientId, Long doctorId, String appointmentDate, String timeSlot) {
//	    
//	    Patient patient = patientRepository.findById(patientId)
//	        .orElseThrow(() -> new RuntimeException("Patient not found"));
//
//	   
//	    Doctor doctor = doctorRepository.findById(doctorId)
//	        .orElseThrow(() -> new RuntimeException("Doctor not found"));
//
//	   
//	    LocalDate date;
//	    try {
//	        date = LocalDate.parse(appointmentDate);
//	    } catch (DateTimeParseException e) {
//	        throw new RuntimeException("Invalid date format. Use yyyy-MM-dd");
//	    }
//
//	   
//	    if (!doctor.getAvailableSlots().contains(timeSlot)) {
//	        throw new RuntimeException("Doctor not available at this time slot.");
//	    }
//	    if (!doctor.getAvailableDate().equals(date)) {
//	        throw new RuntimeException("Doctor not available on this date.");
//	    }
//
//	    // slot is already booked
//	    boolean isBooked = appointmentRepository.existsByDoctorAndAppointmentDateAndSlot(doctor, date, timeSlot);
//	    if (isBooked) {
//	        throw new RuntimeException("This time slot is already booked.");
//	    }
//
//	  
//	    Appointment appointment = new Appointment();
//	    appointment.setPatient(patient);
//	    appointment.setDoctor(doctor);
//	    appointment.setAppointmentDate(date);
//	    appointment.setSlot(timeSlot);
//	    appointment.setStatus(AppointmentStatus.BOOKED);
//
//	    return appointmentRepository.save(appointment);
//	}

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

	
//	 @Transactional
//	    public Appointment bookAppointment(Long doctorId, Long patientId, Long userId, LocalDate date, String slot) {
//	        
//	        Doctor doctor = doctorRepository.findById(doctorId)
//	                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//
//	        
//	        DoctorAvailability availability = doctorAvailabilityRepository
//	                .findByDoctorIdAndAvailableDate(doctorId, date)
//	                .orElseThrow(() -> new RuntimeException("Doctor is not available on this date"));
//
//	        // Check if slot is available
//	        if (!availability.getAvailableSlots().contains(slot)) {
//	            throw new RuntimeException("Selected slot is not available");
//	        }
//
//	        // Fetch patient
//	        Patient patient = patientRepository.findById(patientId)
//	                .orElseThrow(() -> new RuntimeException("Patient not found"));
//
//	        // Check if patient already has an appointment for this doctor and slot
//	        boolean appointmentExists = appointmentRepository.existsByDoctorAndPatientAndAppointmentDateAndSlot(doctor, patient, date, slot);
//	        if (appointmentExists) {
//	            throw new RuntimeException("Patient already has an appointment at this slot.");
//	        }
//
//	        // Remove the booked slot from availability
//	        availability.getAvailableSlots().remove(slot);
//	        doctorAvailabilityRepository.save(availability);  // Update availability
//
//	        // Create and save appointment
//	        Appointment appointment = new Appointment();
//	        appointment.setDoctor(doctor);
//	        appointment.setPatient(patient);
//	        appointment.setUserId(userId);
//	        appointment.setAppointmentDate(date);
//	        appointment.setSlot(slot);
//	        appointment.setStatus(AppointmentStatus.BOOKED);
//
//	        return appointmentRepository.save(appointment);
//	    }

	// reshedule appoitment
//	public void rescheduleAppointment(Long appointmentId, String newDate, String newSlot) {
//		Appointment appointment = appointmentRepository.findById(appointmentId)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
//
//		Doctor doctor = appointment.getDoctor();
//
//		// Parse the date
//		LocalDate parsedDate = parseDate(newDate);
//
//		// Validate doctor's availability
//		if (!doctor.getAvailableDate().equals(parsedDate) || !doctor.getAvailableSlots().contains(newSlot)) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor not available at this date and slot");
//		}
//
//		// Prevent double booking
//		if (appointmentRepository.existsByDoctorAndAppointmentDateAndSlot(doctor, parsedDate, newSlot)) {
//			throw new ResponseStatusException(HttpStatus.CONFLICT, "Doctor is already booked for this slot.");
//		}
//
//		// Update and save appointment
//		appointment.setAppointmentDate(parsedDate);
//		appointment.setSlot(newSlot);
//		appointment.setStatus(AppointmentStatus.BOOKED);
//		appointmentRepository.save(appointment);
//	}

	// Utility method to parse date
//	private LocalDate parseDate(String date) {
//		try {
//			return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		} catch (DateTimeParseException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Use yyyy-MM-dd");
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	//----------------------
	// book now
//	public Appointment bookAppointment(Long patientId, Long doctorId, Long userId, String availableSlot,
//			LocalDate availableDate) {
//
//		Patient patient = patientRepository.findById(patientId).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found: " + patientId));
//
//		Doctor doctor = doctorRepository.findById(doctorId)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found: " + doctorId));
//
//		// Validate slot and date
//		if (!doctor.getAvailableSlots().contains(availableSlot)) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid slot for doctor: " + availableSlot);
//		}
//		if (!doctor.getAvailableDate().equals(availableDate)) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor not available on: " + availableDate);
//		}
//
//		// Check if the doctor is already booked
//		boolean exists = appointmentRepository.existsByDoctorAndAppointmentDateAndSlot(doctor, availableDate,
//				availableSlot);
//
//		if (exists) {
//			throw new ResponseStatusException(HttpStatus.CONFLICT, "Doctor is already booked at this time");
//		}
//
//		// create
//		Appointment appointment = new Appointment();
//		appointment.setPatient(patient);
//		appointment.setDoctor(doctor);
//		appointment.setAppointmentDate(availableDate);
//		appointment.setSlot(availableSlot);
//		appointment.setStatus(AppointmentStatus.BOOKED);
//
//		return appointmentRepository.save(appointment);
//	}
//	public Appointment bookAppointment(Long patientId, Long doctorId, Long userId, String availableSlot, LocalDate availableDate) {
//
//        // Fetch Patient
//        Patient patient = patientRepository.findById(patientId)
//            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found: " + patientId));
//
//        // Fetch Doctor
//        Doctor doctor = doctorRepository.findById(doctorId)
//            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found: " + doctorId));
//
//        // Validate if the slot exists for the doctor
//        if (!doctor.getAvailableSlots().contains(availableSlot)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid slot for doctor: " + availableSlot);
//        }
//
//        if (!doctor.getAvailableDate().equals(availableDate)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor not available on: " + availableDate);
//        }
//
//        // Check if the doctor is already booked
//        boolean exists = appointmentRepository.existsByDoctorAndAppointmentDateAndSlot(doctor, availableDate, availableSlot);
//        if (exists) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Doctor is already booked at this time");
//        }
//
//        // Create and Save the Appointment
//        Appointment appointment = new Appointment();
//        appointment.setPatient(patient);
//        appointment.setDoctor(doctor);
//        appointment.setAppointmentDate(availableDate);
//        appointment.setSlot(availableSlot);
//        appointment.setStatus(AppointmentStatus.BOOKED);
//
//        return appointmentRepository.save(appointment);
//    }

	
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

//	public Appointment bookAppointment(Long doctorId, Long patientId, Long userId, LocalDate date, String slot) {
//        // Fetch doctor
//        Doctor doctor = doctorRepository.findById(doctorId)
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//
//        // Fetch patient
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new RuntimeException("Patient not found"));
//
//        // Create new appointment
//        Appointment appointment = new Appointment();
//        appointment.setDoctor(doctor);
//        appointment.setPatient(patient);
//        appointment.setUserId(userId);
//        appointment.setAppointmentDate(date);
//        appointment.setSlot(slot);
//        appointment.setStatus(AppointmentStatus.BOOKED); // Set status as BOOKED
//
//        return appointmentRepository.save(appointment);
//    }
//


}
