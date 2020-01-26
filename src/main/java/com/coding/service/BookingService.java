package com.coding.service;

import java.util.List;

import com.coding.dto.AddBookingDto;
import com.coding.dto.EditBookingDto;
import com.coding.entiity.Booking;

public interface BookingService {

	public List<Booking> getAllBookings();

	public Booking getBookingById(String id);

	public Booking save(Booking booking);

	public void delete(String id);

	public Booking addBooking(AddBookingDto booking);

	public Booking updateBooking(EditBookingDto booking);

	public void deleteBooking(String id);


}
