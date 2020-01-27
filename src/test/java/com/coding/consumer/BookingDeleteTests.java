package com.coding.consumer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.repository.BookingRepository;
import com.coding.service.impl.BookingServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookingDeleteTests {

	private static final String BOOKING_ID = "abc-cd-ef";

	@Mock
	BookingRepository bookingRepository;

	@InjectMocks
	BookingServiceImpl bookingService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public final void test_delete_booking() {
		bookingService.delete(BOOKING_ID);
		verify(bookingRepository, times(1)).deleteById(BOOKING_ID);
	}

}
