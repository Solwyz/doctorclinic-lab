package com.solwyz.doctorlab.Service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.Booking;
import com.solwyz.doctorlab.Entity.Laboratory;
import com.solwyz.doctorlab.Entity.Test;
import com.solwyz.doctorlab.Repo.BookingRepository;
import com.solwyz.doctorlab.Repo.LabRepository;
import com.solwyz.doctorlab.Repo.TestRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private TestRepository testRepository;
	@Autowired
	private LabRepository labRepository;
	
	
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

	public Booking bookNowForClinic(Booking booking) {
		return bookingRepository.save(booking);
	}

	public List<Booking> getBookingsByUserId(Long userId) {
		return bookingRepository.findByUserId(userId);
	}

	public void cancelBooking(Long id) {
		Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		booking.setStatus("Cancelled");
		bookingRepository.save(booking);
	}


//	public Booking bookNowForTest(Long testId) {
//	
//	    Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
//	   // Laboratory laboratory = labRepository.findById(labId).orElseThrow(() -> new RuntimeException("Laboratory not found"));
//	    Booking booking = new Booking();
//	    booking.setTest(test);
//	   // booking.setLaboratory(laboratory);
//	    return bookingRepository.save(booking);
//	}
	
//	public Booking bookNowForTest(Long testId) {
//        return testRepository.findById(testId)
//            .map(test -> {
//                Booking booking = new Booking();
//                booking.setTest(test);
//                return bookingRepository.save(booking);
//            })
//            .orElseThrow(() -> new EntityNotFoundException("Test with ID " + testId + " not found"));
//    }
//	
	public Booking bookNowForTest(Long testId) {
	    Test test = testRepository.findById(testId)
	            .orElseThrow(() -> new EntityNotFoundException("Test with ID " + testId + " not found"));

	    Booking booking = new Booking();
	    booking.setTest(test);
	    //booking.setStatus("Pending"); // Set default status

	    return bookingRepository.save(booking);
	}


}
