package com.idiada.exercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VehicleDTO implements Serializable{

	private static final long serialVersionUID = -6196872657879605001L;

	private Integer id;
	private String plate;
	private String manufacturer;
	private String make;
	private String commercialName;
	private String vinNumber;
	private Integer capacity;

}
