package com.coding.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditBookingDto {
	@NotEmpty
	private String bookingId;
	@NotEmpty
	private String passengerName;
	@NotEmpty
	private String passengerContactNumber;
	@NotNull
	private Boolean asap = true;
	@NotNull
	private Integer waitingTime;
	@NotNull
	private Integer noOfPassengers;
	@NotNull
	private BigDecimal price;
	@NotNull
	private Integer rating;
	private List<EditTripWaypointDto> tripWayPoints;
}
