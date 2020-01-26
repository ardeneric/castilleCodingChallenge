package com.coding.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingDeleteConsumer {

	@Autowired
	BookingService bookingService;

	@RabbitListener(queues = "${rab.bookingDeleteQueue}")
	public void processBokingDelete(String id) {
		log.info("Message received for booking delete :: " + id);
		try {
			bookingService.delete(id);
			log.info("======= Booking deleted ======");
		} catch (Exception e) {
			log.error("ERROR OCCURRED :: {} ", e);
		}

	}
}
