package com.solwyz.doctorlab.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solwyz.doctorlab.Entity.Booking;
import com.solwyz.doctorlab.Service.BookingService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/booking")
@Tag(name = " booking", description = "APIs for Booking related operations")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/create")
	public ResponseEntity<?> bookAppointment(@RequestBody Booking booking) {
		return ResponseEntity.ok(bookingService.createBooking(booking));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
		return ResponseEntity.ok(bookingService.updateBooking(id, booking));
	}

	@PatchMapping("/status/{id}")
	public ResponseEntity<?> updateBookingStatus(@PathVariable Long id, @RequestParam String status) {
		return ResponseEntity.ok(bookingService.updateBookingStatus(id, status));
	}

	@GetMapping("/status")
	public ResponseEntity<List<Booking>> getBookingsByStatus(@RequestParam String status, @RequestParam Long userId) {
		return ResponseEntity.ok(bookingService.getBookingsByStatus(status, userId));
	}

}
