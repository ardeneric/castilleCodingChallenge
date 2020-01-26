package com.coding.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageAuditConsumer {
	
	@RabbitListener(queues = "${rab.messageAuditQueue}")
	public void processMessageAudit(Object object) {
		log.info("Message received for message audit :: {} ", object);

	}

}
