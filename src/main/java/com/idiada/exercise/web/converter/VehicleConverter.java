package com.idiada.exercise.web.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idiada.exercise.domain.Vehicle;
import com.idiada.exercise.dto.CreateVehicleDTO;
import com.idiada.exercise.dto.VehicleDTO;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class VehicleConverter {
	
	@Autowired
    private ModelMapper modelMapper;

	public VehicleDTO convertToDto(Vehicle vehicle) {
	    return modelMapper.map(vehicle, VehicleDTO.class);
	}

	public Vehicle convertToEntity(VehicleDTO vehicleDTO) {
	    return  modelMapper.map(vehicleDTO, Vehicle.class);
	}
	
	public Vehicle convertToEntity(CreateVehicleDTO createVehicleDTO) {
		return modelMapper.map(createVehicleDTO, Vehicle.class);
	}

	public List<Vehicle> convertList(List<CreateVehicleDTO> createVehicleDTOList)
	{
		return modelMapper.map(createVehicleDTOList,new TypeToken<List<Vehicle>>(){}.getType());
	}

	public List<VehicleDTO> convertListToDTO(List<Vehicle> createdVehicleList)
	{
		return modelMapper.map(createdVehicleList,new TypeToken<List<VehicleDTO>>(){}.getType());
	}

}
