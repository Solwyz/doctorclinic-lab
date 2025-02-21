package com.medo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medo.entity.Appointment;
import com.medo.entity.Doctor;
import com.medo.service.AppointmentService;

@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	// book now
	@PostMapping("/book")
	public ResponseEntity<Appointment> bookAppointment(@RequestParam Long patientId, @RequestParam Long doctorId,
			@RequestParam String appointmentDateTime) {

		Appointment appointment = appointmentService.bookAppointment(patientId, doctorId, appointmentDateTime);
		return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
	}

	// avilable timeslot
	@GetMapping("/{doctorId}/slots")
	public ResponseEntity<List<String>> getAvailableSlots(@PathVariable Long doctorId) {
		return ResponseEntity.ok(appointmentService.getAvailableSlots(doctorId));
	}

	// completedappoitnment by doctor
	@PostMapping(value = "/completed/{appointmentId}")
	public ResponseEntity<String> completedAppointment(@PathVariable Long appointmentId) {
		appointmentService.completedAppointment(appointmentId);
		return ResponseEntity.ok("Appointment completed for id :"+appointmentId);

	}

	// pass patient id
//list of doctors should come while fetching this

	@GetMapping("/completed/{patientId}")
	public ResponseEntity<List<Doctor>> getCompletedAppointments(@PathVariable Long patientId) {
		List<Doctor> completedDoctors = appointmentService.getCompletedAppointments(patientId);
		return ResponseEntity.ok(completedDoctors);
	}

	@GetMapping("/cancelled/{patientId}")
	public ResponseEntity<List<Doctor>> getCancelledAppointments(@PathVariable Long patientId) {
		List<Doctor> cancelledDoctors = appointmentService.getCancelledAppointment(patientId);
		return ResponseEntity.ok(cancelledDoctors);

	}

	@GetMapping("/upcoming/{patientId}")
	public ResponseEntity<List<Doctor>> getUpcomingAppointments(@PathVariable Long patientId) {
		List<Doctor> upcomingDoctors = appointmentService.getUpcomingAppointments(patientId);
		return ResponseEntity.ok(upcomingDoctors);
	}

}
