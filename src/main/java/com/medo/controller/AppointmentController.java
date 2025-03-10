package com.medo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medco.dto.AppointmentRequest;
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
	
	@PostMapping("/booknow")
	public ResponseEntity<Map<String, Object>> bookAppointment(@RequestBody AppointmentRequest request) {
	    Appointment appointment = appointmentService.bookAppointment(request);

	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "Appointment booked successfully!");
	    response.put("appointmentId", appointment.getId());

	    return ResponseEntity.ok(response);
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
	@GetMapping("/completed/{userId}")
	public ResponseEntity<ApiResponse<List<Appointment>>> getCompletedAppointments(@PathVariable Long userId) {
	    List<Appointment> appointments = appointmentService.getCompletedAppointments(userId);
	    return ResponseEntity.ok(new ApiResponse<>("success", appointments));
	}


	@GetMapping("/cancelled/{userId}")
	public ResponseEntity<List<Appointment>> getCancelledAppointments(@PathVariable Long userId) {
	    List<Appointment> cancelledAppointments = appointmentService.getCancelledAppointments(userId);
	    return ResponseEntity.ok(cancelledAppointments);
	}




	@GetMapping("/upcoming/{userId}")
	public ResponseEntity<ApiResponse<List<Appointment>>> getUpcomingAppointments(@PathVariable Long userId) {
	    List<Appointment> appointments = appointmentService.getUpcomingAppointments(userId);
	    return ResponseEntity.ok(new ApiResponse<>("success", appointments));
	}

	
	@PostMapping("/feedbackSubmit")
	public void submitFeedback(@RequestParam Long appointmentId, @RequestParam Integer rating,
			@RequestParam String feedback) {

		appointmentService.submitFeedback(appointmentId, rating, feedback);
	}

//	@PostMapping("/confirmBooking")
//	public ResponseEntity<ApiResponse<Appointment>> confirmBooking(
//	        @RequestParam Long userId,
//	        @RequestParam Long patientId,
//	        @RequestParam Long doctorId,
//	        @RequestParam String appointmentDate,
//	        @RequestParam String timeSlot) {
//
//	    Appointment appointment = appointmentService.confirmBooking(userId, patientId, doctorId, appointmentDate, timeSlot);
//	    return ResponseEntity.ok(new ApiResponse<>("success", appointment));
//	}

	
	

	
//	 @PostMapping("/booknow")
//    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentRequest request) {
//        Appointment appointment = appointmentService.bookAppointment(
//                request.getDoctorId(),
//                request.getPatientId(),
//                request.getUserId(),
//                request.getAvailableDate(),
//                request.getAvailableSlot()
//        );
//        return ResponseEntity.ok(appointment);
//    }

//@PostMapping("/reschedule/{appointmentId}")
//public ResponseEntity<String> rescheduleAppointment(
//        @PathVariable Long appointmentId,
//        @RequestParam String newDate,
//        @RequestParam String newSlot) {
//
//    appointmentService.rescheduleAppointment(appointmentId, newDate, newSlot);
//    return ResponseEntity.ok("Appointment rescheduled successfully.");
//}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	 @GetMapping("/{patientId}/completed")
//	    public ResponseEntity<ApiResponse<List<Doctor>>> getCompletedAppointments(@PathVariable Long patientId) {
//	        List<Doctor> doctors = appointmentService.getCompletedAppointments(patientId);
//	        return ResponseEntity.ok(new ApiResponse<>("success", doctors));
//	    }
//	 

	
	
	
	
	
	
	
	//----------------------
	// book now
//	@PostMapping("/booknow")
//	public ResponseEntity<Appointment> bookAppointment(
//	        @RequestParam Long patientId, 
//	        @RequestParam Long doctorId,
//	        @RequestParam Long userId, 
//	        @RequestParam String availableSlot, 
//	        @RequestParam String availableDate) {
//
//	    Appointment appointment = appointmentService.bookAppointment(
//	        patientId, doctorId, userId, availableSlot, LocalDate.parse(availableDate)
//	    );
//
//	    return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
//	}
	
//	@PostMapping("/booknow")
//    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentRequest request) {
//        System.out.println("Booking API hit with slot: " + request.getAvailableSlot() + " and date: " + request.getAvailableDate());
//
//        Appointment appointment = appointmentService.bookAppointment(
//            request.getPatientId(),
//            request.getDoctorId(),
//            request.getUserId(),
//            request.getAvailableSlot(),
//            LocalDate.parse(request.getAvailableDate()) // Convert String to LocalDate
//        );
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
//    }
	
//	 @PostMapping("/booknow")
//	    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentRequest request) {
//	        Appointment appointment = appointmentService.bookAppointment(
//	                request.getDoctorId(),
//	                request.getPatientId(),
//	                request.getUserId(),
//	                request.getAvailableDate(),  // Now LocalDate, no conversion needed
//	                request.getAvailableSlot()
//	        );
//
//	        return ResponseEntity.ok(appointment);
//	    }
}
