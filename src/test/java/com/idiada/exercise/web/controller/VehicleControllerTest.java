package com.idiada.exercise.web.controller;

import com.idiada.exercise.domain.Vehicle;
import com.idiada.exercise.dto.CreateVehicleDTO;
import com.idiada.exercise.dto.VehicleDTO;
import com.idiada.exercise.services.VehicleService;
import com.idiada.exercise.web.converter.VehicleConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class VehicleControllerTest
{

	@InjectMocks
	private VehicleController vehicleController;

	@Mock
	private VehicleConverter vehicleConverter;

	@Mock
	private VehicleService vehicleService;

	private List<Vehicle> vehiclesMock = new ArrayList<>();


	@BeforeEach
	public void setUp()
	{

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setId(1);
		vehicle1.setPlate("AAA-111");
		vehicle1.setMake("Ford");
		vehicle1.setManufacturer("Ford");
		vehicle1.setCommercialName("Ford");
		vehicle1.setVinNumber("123456789");
		vehicle1.setCapacity(4);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setId(2);
		vehicle2.setPlate("BBB-222");
		vehicle2.setMake("Chevrolet");
		vehicle2.setManufacturer("Chevrolet");
		vehicle2.setCommercialName("Chevrolet");
		vehicle2.setVinNumber("987654321");
		vehicle2.setCapacity(4);

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setId(3);
		vehicle3.setPlate("CCC-333");
		vehicle3.setMake("Fiat");
		vehicle3.setManufacturer("Fiat");
		vehicle3.setCommercialName("Fiat");
		vehicle3.setVinNumber("123456789");
		vehicle3.setCapacity(4);

		vehiclesMock.add(vehicle1);
		vehiclesMock.add(vehicle2);
		vehiclesMock.add(vehicle3);

	}

    @Test
    void createVehicle() throws Exception {

		Mockito.when(vehicleService.listAllVehicles()).thenReturn(vehiclesMock);

		List<VehicleDTO> list = vehicleController.getAllVehicles();

		assertEquals(vehiclesMock.size(), list.size());

	}

	@Test
	public void whenCreateVehicleThenOK() throws Exception
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Vehicle vehicleCreated = vehiclesMock.get(0);

		VehicleDTO vehicleDTO = vehicleConverter.convertToDto(vehicleCreated);

		CreateVehicleDTO vehicle = new CreateVehicleDTO();
		vehicle.setPlate("AAA-111");
		vehicle.setMake("Ford");
		vehicle.setManufacturer("Ford");
		vehicle.setCommercialName("Ford");
		vehicle.setVinNumber("123456789");
		vehicle.setCapacity(4);

		Mockito.when(vehicleService.createVehicle(Mockito.any(Vehicle.class))).thenReturn(vehicleCreated);
		Mockito.when(vehicleConverter.convertToEntity(Mockito.any(CreateVehicleDTO.class))).thenReturn(vehicleCreated);
		Mockito.when(vehicleConverter.convertToDto(Mockito.any(Vehicle.class))).thenReturn(vehicleDTO);
		VehicleDTO vehicle2 = vehicleController.createVehicle(vehicle);

		assertEquals(vehicleDTO, vehicle2);

	}

	@Test
	public void whenDeleteVehicleThenOK() throws Exception
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		vehicleController.deleteVehicle(1);
		Mockito.verify(vehicleService, Mockito.times(1)).deleteVehicleById(Mockito.anyInt());

	}
}
