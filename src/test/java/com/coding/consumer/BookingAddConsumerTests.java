package com.coding.consumer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.consumers.BookingAddConsumer;
import com.coding.dto.AddBookingDto;
import com.coding.dto.TripWaypointDto;
import com.coding.entity.Booking;
import com.coding.entity.TripWaypoint;
import com.coding.repository.BookingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookingAddConsumerTests {

	@Mock
	BookingRepository bookingRepository;

	@Autowired
	BookingAddConsumer bookingAddConsumer;

	@Rule
	public final OutputCapture outputCapture = new OutputCapture();

	Booking booking;

	AddBookingDto addBookingDto;

	TripWaypoint tripWaypoint;

	TripWaypointDto tripWaypointDto;

	List<TripWaypoint> tripWaypointList = new ArrayList<>();

	List<TripWaypointDto> tripWaypointDtoList = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		tripWaypoint = new TripWaypoint();
		tripWaypoint.setBooking(booking);
		tripWaypoint.setLastStop(true);
		tripWaypoint.setLat(-34736.3);
		tripWaypoint.setLng(466583.9);
		tripWaypoint.setLocality("locality");
		tripWaypoint.setTripWayPointTimestamp(Instant.now());
		tripWaypointList.add(tripWaypoint);

		booking = new Booking();
		booking.setCreatedOn(Instant.now());
		booking.setLastModifiedOn(Instant.now());
		booking.setNoOfPassengers(9);
		booking.setPassengerContactNumber("23324578394");
		booking.setPassengerName("John Doe");
		booking.setPickupTime(OffsetDateTime.now());
		booking.setPrice(BigDecimal.TEN);
		booking.setRating(5);
		booking.setWaitingTime(88);
		booking.setTripWayPoints(tripWaypointList);

		tripWaypointDto = new TripWaypointDto();
		tripWaypointDto.setLastStop(true);
		tripWaypoint.setLat(-34736.3);
		tripWaypointDto.setLng(466583.9);
		tripWaypointDto.setLocality("locality");
		tripWaypointDtoList.add(tripWaypointDto);

		addBookingDto = new AddBookingDto();
		addBookingDto.setNoOfPassengers(9);
		addBookingDto.setPassengerContactNumber("23324578394");
		addBookingDto.setPassengerName("John Doe");
		addBookingDto.setPrice(BigDecimal.TEN);
		addBookingDto.setRating(5);
		addBookingDto.setWaitingTime(88);
		addBookingDto.setTripWayPoints(tripWaypointDtoList);
	}

	@Test
	public final void test_add_booking() {
		when(bookingRepository.save(booking)).thenReturn(booking);
		bookingAddConsumer.processBokingAdd(addBookingDto);
		outputCapture.expect(containsString("Booking added"));
	}

}
