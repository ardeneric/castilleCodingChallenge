package com.coding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.entiity.Booking;
import com.coding.service.BookingService;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	public Booking getBookingById(String id) {
		return bookingService.getBookingById(id);
	}

	public Booking addBooking(Booking booking) {
		return bookingService.addBooking(booking);
	}

	public Booking updateBooking(Booking booking) {
		return bookingService.updateBooking(booking);
	}

	public void deleteBooking(String id) {
		bookingService.deleteBooking(id);

	}

}
