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
import com.medo.pojo.response.ApiResponse;
import com.medo.service.AppointmentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	// book now
	@PostMapping("/book")
	public ResponseEntity<Appointment> bookAppointment( @RequestParam Long patientId, @RequestParam Long doctorId, 
			@RequestParam String appointmentDateTime) {

	    Appointment appointment = appointmentService.bookAppointment(patientId, doctorId, appointmentDateTime);
	    return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
	}

	


	// completedappoitnment by doctor
	@PostMapping(value = "/completed/{appointmentId}")
	public ResponseEntity<String> completedAppointment(@PathVariable Long appointmentId) {
		appointmentService.completedAppointment(appointmentId);
		return ResponseEntity.ok("Appointment completed for id:"+appointmentId);

	}
	

	// pass patient id
//list of doctors should come while fetching this

	//check
	 @GetMapping("/{patientId}/completed")
	    public ResponseEntity<ApiResponse<List<Doctor>>> getCompletedAppointments(@PathVariable Long patientId) {
	        List<Doctor> doctors = appointmentService.getCompletedAppointments(patientId);
	        return ResponseEntity.ok(new ApiResponse<>("success", doctors));
	    }
	 
	 //cancelappointment
	 @PostMapping("/{appointmentId}/cancel")
	 public ResponseEntity<String> cancelAppointment(@PathVariable Long appointmentId) {
	     appointmentService.cancelAppointment(appointmentId);
	     return ResponseEntity.ok("Appointment cancelled successfully for ID: " + appointmentId);
	 }

	 
	 @GetMapping("/{patientId}/cancelled")
	    public ResponseEntity<ApiResponse<List<Doctor>>> getCancelledAppointments(@PathVariable Long patientId) {
	        List<Doctor> doctors = appointmentService.getCancelledAppointments(patientId);
	        return ResponseEntity.ok(new ApiResponse<>("success", doctors));
	    }

//check	 
	    @GetMapping("/{patientId}/upcoming")
	    public ResponseEntity<ApiResponse<List<Doctor>>> getUpcomingAppointments(@PathVariable Long patientId) {
	        List<Doctor> doctors = appointmentService.getUpcomingAppointments(patientId);
	        return ResponseEntity.ok(new ApiResponse<>("success", doctors));
	    }

	    
	    
	    @PostMapping("/reschedule/{appointmentId}")
	    public ResponseEntity<String> rescheduleAppointment(
	            @PathVariable Long appointmentId,
	            @RequestParam String newDateTime) {

	        appointmentService.rescheduleAppointment(appointmentId, newDateTime);
	        return ResponseEntity.ok("Appointment rescheduled successfully.");
	    }

}
