package com.medo.controller;

import java.time.LocalDate;
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
import com.medo.pojo.response.ApiResponse;
import com.medo.service.AppointmentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	// book now
	@PostMapping("/booknow")
	public ResponseEntity<Appointment> bookAppointment(
	        @RequestParam Long patientId, 
	        @RequestParam Long doctorId,
	        @RequestParam Long userId, 
	        @RequestParam String availableSlot, 
	        @RequestParam String availableDate) {

	    Appointment appointment = appointmentService.bookAppointment(
	        patientId, doctorId, userId, availableSlot, LocalDate.parse(availableDate)
	    );

	    return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
	}
	
	
	@PostMapping("/reschedule/{appointmentId}")
	public ResponseEntity<String> rescheduleAppointment(
	        @PathVariable Long appointmentId,
	        @RequestParam String newDate,
	        @RequestParam String newSlot) {

	    appointmentService.rescheduleAppointment(appointmentId, newDate, newSlot);
	    return ResponseEntity.ok("Appointment rescheduled successfully.");
	}


	// completedappoitnment by doctor
	@PostMapping(value = "/completed/{appointmentId}")
	public ResponseEntity<String> completedAppointment(@PathVariable Long appointmentId) {
		appointmentService.completedAppointment(appointmentId);
		return ResponseEntity.ok("Appointment completed for id:" + appointmentId);

	}

	// pass patient id
//list of doctors should come while fetching this

	

	// cancelappointment
	@PostMapping("/cancel/{appointmentId}/{patientId}")
	public ResponseEntity<String> cancelAppointment(
	        @PathVariable Long appointmentId,
	        @PathVariable Long patientId) {

	    appointmentService.cancelAppointment(appointmentId, patientId);
	    return ResponseEntity.ok("Appointment cancelled successfully for ID: " + appointmentId);
	}

	
	// check
	@GetMapping("/completed")
	public ResponseEntity<ApiResponse<List<Appointment>>> getCompletedAppointments() {
	    List<Appointment> appointments = appointmentService.getCompletedAppointments();
	    return ResponseEntity.ok(new ApiResponse<>("success", appointments));
	}


	@GetMapping("/cancelled")
	public ResponseEntity<List<Appointment>> getCancelledAppointments() {
	    List<Appointment> cancelledAppointments = appointmentService.getCancelledAppointments();
	    return ResponseEntity.ok(cancelledAppointments);
	}



	@GetMapping("/upcoming/{userId}")
	public ResponseEntity<ApiResponse<List<Appointment>>> getUpcomingAppointments(@PathVariable Long userId) {
	    List<Appointment> appointments = appointmentService.getUpcomingAppointments(userId);
	    return ResponseEntity.ok(new ApiResponse<>("success", appointments));
	}


	@PostMapping("/confirmBooking")
	public ResponseEntity<ApiResponse<Appointment>> confirmBooking(
	        @RequestParam Long userId,
	        @RequestParam Long patientId,
	        @RequestParam Long doctorId,
	        @RequestParam String appointmentDate,
	        @RequestParam String timeSlot) {

	    Appointment appointment = appointmentService.confirmBooking(userId, patientId, doctorId, appointmentDate, timeSlot);
	    return ResponseEntity.ok(new ApiResponse<>("success", appointment));
	}

	@PostMapping("/feedbackSubmit")
	public void submitFeedback(@RequestParam Long appointmentId, @RequestParam Integer rating,
			@RequestParam String feedback) {

		appointmentService.submitFeedback(appointmentId, rating, feedback);
	}
	

//	 @GetMapping("/{patientId}/completed")
//	    public ResponseEntity<ApiResponse<List<Doctor>>> getCompletedAppointments(@PathVariable Long patientId) {
//	        List<Doctor> doctors = appointmentService.getCompletedAppointments(patientId);
//	        return ResponseEntity.ok(new ApiResponse<>("success", doctors));
//	    }
//	 

}
