package com.idiada.exercise.services;

import java.util.List;

import com.idiada.exercise.domain.Vehicle;
import org.springframework.stereotype.Service;

public interface VehicleService {
	
	List<Vehicle> listAllVehicles();
	
	Vehicle createVehicle(Vehicle vehicle);
	
	List<Vehicle> createVehicles(List<Vehicle> vehicles);

    Vehicle updateVehicle(Vehicle vehicle) throws Exception;

    Vehicle duplicateVehicle(Vehicle vehicle, Integer id) throws Exception;

    void deleteVehicleById (Integer id) throws Exception;
}
