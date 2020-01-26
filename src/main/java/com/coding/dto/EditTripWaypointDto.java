package com.coding.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditTripWaypointDto {
	@NotEmpty
	private String tripWaypointId;
	@NotNull
	private Boolean lastStop;
	@NotEmpty
	private String locality;
	@NotNull
	private Double lat;
	@NotNull
	private Double lng;
}
