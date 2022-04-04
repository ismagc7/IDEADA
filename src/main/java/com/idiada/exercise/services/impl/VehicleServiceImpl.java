package com.idiada.exercise.services.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idiada.exercise.domain.Vehicle;
import com.idiada.exercise.persistence.repository.VehicleRepository;
import com.idiada.exercise.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	public List<Vehicle> listAllVehicles() {
		return vehicleRepository.findAll();
	}
	
	@Override
	public Vehicle createVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@Override
	public List<Vehicle> createVehicles(List<Vehicle> vehicles) {
		return vehicleRepository.saveAll(vehicles);
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle) throws Exception {

		if (!vehicleRepository.existsById(vehicle.getId()))
		{
			throw new Exception("Vehicle does not exist");
		}
		Vehicle vehicleUpdated = vehicleRepository.getOne(vehicle.getId());

		vehicleUpdated.setPlate(vehicle.getPlate());
		vehicleUpdated.setManufacturer(vehicle.getManufacturer());
		vehicleUpdated.setMake(vehicle.getMake());
		vehicleUpdated.setCommercialName(vehicle.getCommercialName());
		vehicleUpdated.setVinNumber(vehicle.getVinNumber());
		vehicleUpdated.setCapacity(vehicle.getCapacity());

		return vehicleRepository.saveAndFlush(vehicleUpdated);
	}

	@Override
	public void deleteVehicleById(Integer id) throws Exception {

		if (!vehicleRepository.existsById(id))
		{
			throw new Exception("Vehicle does not exist");
		}

		vehicleRepository.deleteById(id);

	}

	@Override
	public Vehicle duplicateVehicle(Vehicle vehicle, Integer id) throws Exception {

		if (!vehicleRepository.existsById(id))
		{
			throw new Exception("Can't be cloned because don't exist");
		}

		Vehicle vehicleCloned = new Vehicle();

		vehicleCloned.setPlate(vehicle.getPlate());
		vehicleCloned.setMake(vehicle.getMake());
		vehicleCloned.setManufacturer(vehicle.getManufacturer());
		vehicleCloned.setVinNumber(vehicle.getVinNumber());
		vehicleCloned.setCommercialName(vehicle.getCommercialName());
		vehicleCloned.setCapacity(vehicle.getCapacity());

		return vehicleRepository.save(vehicleCloned);
	}
}
