package com.coding.consumers;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.dto.AddBookingDto;
import com.coding.dto.TripWaypointDto;
import com.coding.entity.Booking;
import com.coding.entity.TripWaypoint;
import com.coding.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingAddConsumer {

	@Autowired
	BookingService bookingService;

	@RabbitListener(queues = "${rab.bookingAddQueue}")
	public void processBokingAdd(AddBookingDto bookingDto) {
		log.info("Message received for booking add :: {} ", bookingDto);
		try {
			Booking booking = setBooking(bookingDto);
			bookingService.save(booking);
			log.info("====== Booking added ======");
		} catch (Exception e) {
			log.error("ERROR OCCURED :: {} ", e);
		}

	}

	private List<TripWaypoint> setTripWaypoint(List<TripWaypointDto> tripWaypointDtoList, Booking booking) {
		List<TripWaypoint> tripWaypointList = new ArrayList<>();
		tripWaypointDtoList.stream().forEach(c -> {
			TripWaypoint tripWaypoint = new TripWaypoint();
			tripWaypoint.setBooking(booking);
			tripWaypoint.setLastStop(c.getLastStop());
			tripWaypoint.setLat(c.getLat());
			tripWaypoint.setLng(c.getLng());
			tripWaypoint.setLocality(c.getLocality());
			tripWaypoint.setTripWayPointTimestamp(Instant.now());
			tripWaypointList.add(tripWaypoint);
		});
		return tripWaypointList;
	}

	private Booking setBooking(AddBookingDto bookingDto) {
		Booking booking = new Booking();
		booking.setAsap(bookingDto.getAsap());
		booking.setCreatedOn(Instant.now());
		booking.setLastModifiedOn(Instant.now());
		booking.setNoOfPassengers(bookingDto.getNoOfPassengers());
		booking.setPassengerContactNumber(bookingDto.getPassengerContactNumber());
		booking.setPassengerName(bookingDto.getPassengerName());
		booking.setPickupTime(OffsetDateTime.now());
		booking.setPrice(bookingDto.getPrice());
		booking.setRating(bookingDto.getRating());
		booking.setWaitingTime(bookingDto.getWaitingTime());
		booking.setTripWayPoints(setTripWaypoint(bookingDto.getTripWayPoints(), booking));
		return booking;
	}

}
