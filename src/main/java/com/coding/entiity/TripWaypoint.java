package com.coding.entiity;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class TripWaypoint {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String tripWayPointId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking", referencedColumnName = "bookingId")
	private Booking booking;
	private Boolean lastStop;
	private String locality;
	private Double lat;
	private Double lng;
	@JsonIgnore
	private Instant tripWayPointTimestamp;
}