package com.coding.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class TripWaypoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1811307085699522149L;
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String tripWayPointId;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "booking", referencedColumnName = "bookingId")
	private Booking booking;
	private Boolean lastStop;
	private String locality;
	private Double lat;
	private Double lng;
	@JsonIgnore
	private Instant tripWayPointTimestamp;
}