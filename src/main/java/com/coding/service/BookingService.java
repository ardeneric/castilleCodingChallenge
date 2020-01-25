package com.coding.service;

import java.util.List;

import com.coding.entiity.Booking;

public interface BookingService {

	public List<Booking> getAllBookings();

	public Booking getBookingById(String id);
	
	public Booking addBooking(Booking booking);
	
	public Booking updateBooking(Booking booking);
	
	public void deleteBooking(String id);

}
