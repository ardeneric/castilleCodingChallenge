package com.coding.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TripWaypointDto {
	@NotNull
	private Boolean lastStop;
	@NotEmpty
	private String locality;
	@NotNull
	private Double lat;
	@NotNull
	private Double lng;
}
