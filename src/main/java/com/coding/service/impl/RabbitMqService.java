package com.coding.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.dto.RabbitDto;
import com.coding.entiity.Booking;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMqService {
	private static final String MESSAGE_SENT = "Message sent as: {}";

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	RabbitDto rabbitDto;

	public void publishBookingAdd(Booking booking) {
		rabbitTemplate.convertAndSend(rabbitDto.getMessageExchange(), rabbitDto.getBookingAddKey(), booking);
		log.info(MESSAGE_SENT, booking.toString());
	}

	public void publishBookingEdit(Booking booking) {
		rabbitTemplate.convertAndSend(rabbitDto.getMessageExchange(), rabbitDto.getBookingEditKey(), booking);
		log.info(MESSAGE_SENT, booking.toString());
	}

	public void publishBookingDelete(String id) {
		rabbitTemplate.convertAndSend(rabbitDto.getMessageExchange(), rabbitDto.getBookingDeleteKey(), id);
		log.info(MESSAGE_SENT +  id);
	}

}
