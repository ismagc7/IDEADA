package com.idiada.exercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DuplicateVehicleDTO implements Serializable{

	private static final long serialVersionUID = -6196872657879605001L;

	private Integer id;
	private String plate;

}
