package com.coding.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.dto.AddBookingDto;
import com.coding.dto.EditBookingDto;
import com.coding.entity.Booking;
import com.coding.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/booking")
@ControllerAdvice
@Slf4j
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
	public Booking addBooking(@RequestBody @Valid AddBookingDto booking) {
		return bookingService.addBooking(booking);
	}

	@PostMapping("/update")
	public Booking updateBooking(@RequestBody @Valid EditBookingDto booking) {
		return bookingService.updateBooking(booking);
	}

	@DeleteMapping("/{id}")
	public void deleteBooking(@PathVariable String id) {
		bookingService.deleteBooking(id);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneralException(Exception e) {
		log.error("Error occured :: {} ", e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
