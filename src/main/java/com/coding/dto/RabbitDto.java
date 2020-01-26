package com.coding.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "rab")
public class RabbitDto {
	private String bookingExchange;
	private String messageExchange;
	private String messageAuditKey;
	private String bookingAddKey;
	private String bookingEditKey;
	private String bookingDeleteKey;
	private String bookingAddQueue;
	private String bookingEditQueue;
	private String bookingDeleteQueue;
	private String messageAuditQueue;
}
