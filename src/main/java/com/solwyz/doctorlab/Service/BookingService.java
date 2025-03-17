package com.solwyz.doctorlab.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.Booking;
import com.solwyz.doctorlab.Repo.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	public Booking updateBooking(Long id, Booking bookingDetails) {
		Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		booking.setDoctor(bookingDetails.getDoctor());
		booking.setPatient(bookingDetails.getPatient());
		booking.setSlot(bookingDetails.getSlot());
		booking.setDate(bookingDetails.getDate());
		booking.setStatus(bookingDetails.getStatus());
		return bookingRepository.save(booking);
	}

	public Booking updateBookingStatus(Long id, String status) {
		Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		booking.setStatus(status);
		return bookingRepository.save(booking);
	}

	public List<Booking> getBookingsByStatus(String status, Long userId) {
		return bookingRepository.findByStatusAndUserId(status, userId);
	}

}
