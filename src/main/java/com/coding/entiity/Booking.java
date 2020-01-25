package com.coding.entiity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table
public class Booking {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String bookingId;
	private String passengerName;
	private String passengerContactNumber;
	private OffsetDateTime pickupTime;
	private Boolean asap = true;
	private Integer waitingTime;
	private Integer noOfPassengers;
	private BigDecimal price;
	private Integer rating;
	private Instant createdOn;
	private Instant lastModifiedOn;
	@OneToMany(mappedBy="booking")
	private List<TripWaypoint> tripWayPoints;

}