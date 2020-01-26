package com.coding.consumers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.dto.EditBookingDto;
import com.coding.dto.EditTripWaypointDto;
import com.coding.entiity.Booking;
import com.coding.entiity.TripWaypoint;
import com.coding.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingEditConsumer {

	@Autowired
	BookingService bookingService;

	@RabbitListener(queues = "${rab.bookingEditQueue}")
	public void processBokingEdit(EditBookingDto editBooking) {
		log.info("Message received for booking edit :: {} ", editBooking);
		try {
			Booking booking = setBooking(editBooking);
			bookingService.save(booking);
			log.info("===== Booking updated ======");
		} catch (Exception e) {
			log.error("ERROR OCCURED :: {} ", e);
		}

	}

	private List<TripWaypoint> setTripWaypoint(List<EditTripWaypointDto> tripWaypointDtoList, Booking booking) {
		List<TripWaypoint> tripWaypointList = new ArrayList<>();
		tripWaypointDtoList.stream().forEach(c -> {
			TripWaypoint tripWaypoint = new TripWaypoint();
			tripWaypoint.setTripWayPointId(c.getTripWaypointId());
			tripWaypoint.setBooking(booking);
			tripWaypoint.setLastStop(c.getLastStop());
			tripWaypoint.setLat(c.getLat());
			tripWaypoint.setLng(c.getLng());
			tripWaypoint.setLocality(c.getLocality());
			tripWaypointList.add(tripWaypoint);
		});
		return tripWaypointList;
	}

	private Booking setBooking(EditBookingDto bookingDto) {
		Booking booking = new Booking();
		booking.setAsap(bookingDto.getAsap());
		booking.setLastModifiedOn(Instant.now());
		booking.setNoOfPassengers(bookingDto.getNoOfPassengers());
		booking.setPassengerContactNumber(bookingDto.getPassengerContactNumber());
		booking.setPassengerName(bookingDto.getPassengerName());
		booking.setPrice(bookingDto.getPrice());
		booking.setRating(bookingDto.getRating());
		booking.setWaitingTime(bookingDto.getWaitingTime());
		booking.setBookingId(bookingDto.getBookingId());
		booking.setTripWayPoints(setTripWaypoint(bookingDto.getTripWayPoints(), booking));
		return booking;
	}

}
