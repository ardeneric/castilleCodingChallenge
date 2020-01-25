package com.coding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.entiity.Booking;
import com.coding.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping("/all")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@GetMapping
	public Booking getBookingById(@RequestParam String bookingId) {
		return bookingService.getBookingById(bookingId);
	}

	@PostMapping("/save")
	public Booking addBooking(@RequestBody Booking booking) {
		return bookingService.addBooking(booking);
	}

	@PostMapping("/update")
	public Booking updateBooking(Booking booking) {
		return bookingService.updateBooking(booking);
	}

	@DeleteMapping("/{id}")
	public void deleteBooking(@PathVariable String id) {
		bookingService.deleteBooking(id);

	}

}
