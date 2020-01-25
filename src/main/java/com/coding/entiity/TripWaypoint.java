package com.coding.entiity;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
public class TripWaypoint {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID tripWayPointId;
	@ManyToOne
	@JoinColumn(name = "booking", referencedColumnName = "bookingId")
	private Booking booking;
	private Boolean lastStop;
	private String locality;
	private Double lat;
	private Double lng;
	private Instant tripWayPointTimestamp;
}