package com.coding.consumer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.coding.consumers.BookingEditConsumer;
import com.coding.dto.EditBookingDto;
import com.coding.dto.EditTripWaypointDto;
import com.coding.entiity.Booking;
import com.coding.entiity.TripWaypoint;
import com.coding.repository.BookingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookingEditConsumerTests {

	private static final String BOOKING_ID = "abc-cd-ef";

	private static final String TRIP_WAYPOINT_ID = "ij-jk";

	@Mock
	BookingRepository bookingRepository;

	@Autowired
	BookingEditConsumer bookingEditConsumer;

	@Rule
	public final OutputCapture outputCapture = new OutputCapture();

	Booking booking;

	EditBookingDto editBookingDto;

	TripWaypoint tripWaypoint;

	EditTripWaypointDto tripWaypointDto;

	List<TripWaypoint> tripWaypointList = new ArrayList<>();

	List<EditTripWaypointDto> tripWaypointDtoList = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		tripWaypoint = new TripWaypoint();
		tripWaypoint.setBooking(booking);
		tripWaypoint.setLastStop(true);
		tripWaypoint.setLat(-34736.3);
		tripWaypoint.setLng(466583.9);
		tripWaypoint.setLocality("locality");
		tripWaypoint.setTripWayPointId(TRIP_WAYPOINT_ID);
		tripWaypointList.add(tripWaypoint);

		booking = new Booking();
		booking.setBookingId(BOOKING_ID);
		booking.setNoOfPassengers(9);
		booking.setPassengerContactNumber("23324578394");
		booking.setPassengerName("John Doe");
		booking.setRating(5);
		booking.setWaitingTime(88);
		booking.setTripWayPoints(tripWaypointList);

		tripWaypointDto = new EditTripWaypointDto();
		tripWaypointDto.setLastStop(true);
		tripWaypoint.setLat(-34736.3);
		tripWaypointDto.setLng(466583.9);
		tripWaypointDto.setLocality("locality");
		tripWaypointDto.setTripWaypointId(TRIP_WAYPOINT_ID);
		tripWaypointDtoList.add(tripWaypointDto);

		editBookingDto = new EditBookingDto();
		editBookingDto.setBookingId(BOOKING_ID);
		editBookingDto.setNoOfPassengers(9);
		editBookingDto.setPassengerContactNumber("23324578394");
		editBookingDto.setPassengerName("John Doe");
		editBookingDto.setPrice(BigDecimal.TEN);
		editBookingDto.setRating(5);
		editBookingDto.setWaitingTime(88);
		editBookingDto.setTripWayPoints(tripWaypointDtoList);
	}

	@Test
	public final void test_edit_booking() {
		when(bookingRepository.save(booking)).thenReturn(booking);
		bookingEditConsumer.processBokingEdit(editBookingDto);
		outputCapture.expect(containsString("Booking updated"));
	}

}
