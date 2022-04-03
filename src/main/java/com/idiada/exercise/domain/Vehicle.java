package com.idiada.exercise.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@Entity
@Table(name="Vehicle")
public class Vehicle implements Serializable{

	private static final long serialVersionUID = -6196872657879605001L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NaturalId
    @Column(nullable = false, unique = true)
	private String plate;
	
	@Column(nullable = false)
	private String manufacturer;
	
	@Column(nullable = false)
	private String make;
	
	@Column(nullable = false)
	private String commercialName;
	
	@Column(nullable = false)
	private String vinNumber;
	
	@Column(nullable = true)
	private Integer capacity;
}
