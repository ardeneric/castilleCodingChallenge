package com.coding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.entiity.Booking;
import com.coding.repository.BookingRepository;
import com.coding.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	RabbitMqService rabbitMqService;

	@Override
	public List<Booking> getAllBookings() {
		log.info("====== Getting all bookings ========");
		return bookingRepository.findAll();
	}

	@Override
	public Booking getBookingById(String id) {
		log.info("===== Getting booking by id :: " + id);
		return bookingRepository.findById(id).orElse(new Booking());
	}

	@Override
	public Booking addBooking(Booking booking) {
		log.info("===== Adding booking :: {} ", booking);
		rabbitMqService.publishBookingAdd(booking);
		return null;
	}

	@Override
	public Booking updateBooking(Booking booking) {
		log.info("====== Updating booking :: {} ", booking);
		// publish to queue
		return null;
	}

	@Override
	public void deleteBooking(String id) {
		log.info("===== Deleting booking :: ", id);
		// pubish to queue

	}

}
