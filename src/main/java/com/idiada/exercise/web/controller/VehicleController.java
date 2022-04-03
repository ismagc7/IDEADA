package com.idiada.exercise.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.idiada.exercise.domain.Vehicle;
import com.idiada.exercise.dto.CreateVehicleDTO;
import com.idiada.exercise.dto.VehicleDTO;
import com.idiada.exercise.services.VehicleService;
import com.idiada.exercise.web.converter.VehicleConverter;


@RestController
@RequestMapping("/vehicles")
public class VehicleController extends CommonController{
	
	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private VehicleConverter vehicleConverter;
	
	@GetMapping
    @ResponseBody
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.listAllVehicles()
				.stream()
				.map(vehicleConverter::convertToDto)
          		.collect(Collectors.toList());
    }
	
	@PostMapping
	@ResponseBody
	public VehicleDTO createVehicle(@RequestBody CreateVehicleDTO dto){
		Vehicle vehicle = vehicleService.createVehicle(vehicleConverter.convertToEntity(dto));
		return vehicleConverter.convertToDto(vehicle);
	}

	@PostMapping(value="/list")
	@ResponseBody
	public List<VehicleDTO> createVehicleList(@RequestBody List<CreateVehicleDTO> dtos){
		List<Vehicle> vehicleList = vehicleService.createVehicles(vehicleConverter.convertList(dtos));
		return vehicleConverter.convertListToDTO(vehicleList);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteVehicle(@PathVariable Integer id) throws Exception {
		vehicleService.deleteVehicleById(id);
	}

	@PutMapping
	@ResponseBody
	public VehicleDTO updateVehicle(@RequestBody VehicleDTO dto) throws Exception {
		Vehicle vehicleUpdated = vehicleService.updateVehicle(vehicleConverter.convertToEntity(dto));
		return vehicleConverter.convertToDto(vehicleUpdated);
	}

	@PostMapping(value="/{id}/duplicate")
	@ResponseBody
	public VehicleDTO cloneVehicle(@RequestBody CreateVehicleDTO vehicle,@PathVariable Integer id) throws Exception {
		Vehicle vehicleCloned = vehicleService.duplicateVehicle(vehicleConverter.convertToEntity(vehicle), id);
		return vehicleConverter.convertToDto(vehicleCloned);
	}

}
