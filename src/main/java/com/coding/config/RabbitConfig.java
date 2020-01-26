package com.coding.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coding.dto.RabbitDto;

@Configuration
public class RabbitConfig {

	@Autowired
	RabbitDto rabbitDto;

	// Declare Exchanges
	@Bean
	TopicExchange bookingExchange() {
		return new TopicExchange(rabbitDto.getBookingExchange());
	}

	@Bean
	FanoutExchange messageExchange() {
		return new FanoutExchange(rabbitDto.getMessageExchange());
	}

	// Declare Queues
	@Bean
	Queue bookingAddQueue() {
		return new Queue(rabbitDto.getBookingAddQueue(), true);
	}

	@Bean
	Queue bookingEditQueue() {
		return new Queue(rabbitDto.getBookingEditQueue(), true);
	}

	@Bean
	Queue bookingDeleteQueue() {
		return new Queue(rabbitDto.getBookingDeleteQueue(), true);
	}

	@Bean
	Queue messageAuditQueue() {
		return new Queue(rabbitDto.getMessageAuditQueue(), true);
	}

	// MessageExchange bound to the MessageAuditQueue and the BookingExchange.
	@Bean
	Binding messageExchangeAndExchangeBinding(TopicExchange bookingExchange, FanoutExchange messageExchange) {
		return BindingBuilder.bind(bookingExchange).to(messageExchange);
	}

	@Bean
	Binding messageAuditExchangeBinding(@Qualifier("messageAuditQueue") Queue messagAuditQueue,
			FanoutExchange messageExchange) {
		return BindingBuilder.bind(new Queue(rabbitDto.getMessageAuditQueue(), true)).to(messageExchange);
	}

	@Bean
	Binding bookingAddExchangeBinding(Queue bookingAddQueue, TopicExchange bookingExchange) {
		return BindingBuilder.bind(bookingAddQueue).to(bookingExchange).with(rabbitDto.getBookingAddKey());
	}

	@Bean
	Binding bookingEditExchangeBinding(Queue bookingEditQueue, TopicExchange bookingExchange) {
		return BindingBuilder.bind(bookingEditQueue).to(bookingExchange).with(rabbitDto.getBookingEditKey());
	}

	@Bean
	Binding bookingDeleteExchangeBinding(Queue bookingDeleteQueue, TopicExchange bookingExchange) {
		return BindingBuilder.bind(bookingDeleteQueue).to(bookingExchange).with(rabbitDto.getBookingDeleteKey());
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
