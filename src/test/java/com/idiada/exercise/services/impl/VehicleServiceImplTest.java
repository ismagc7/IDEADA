package com.idiada.exercise.services.impl;

import com.idiada.exercise.domain.Vehicle;
import com.idiada.exercise.persistence.repository.VehicleRepository;
import com.idiada.exercise.services.VehicleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VehicleServiceImplTest
{

	@Autowired
	private VehicleService vehicleService;

	@MockBean
	private VehicleRepository vehicleRepository;

	private List<Vehicle> vehiclesMock = new ArrayList<>();

	@BeforeEach
	public void init() {

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setPlate("AAA-111");
		vehicle1.setMake("Ford");
		vehicle1.setManufacturer("Ford");
		vehicle1.setCommercialName("Ford");
		vehicle1.setVinNumber("123456789");
		vehicle1.setCapacity(4);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setPlate("BBB-222");
		vehicle2.setMake("Chevrolet");
		vehicle2.setManufacturer("Chevrolet");
		vehicle2.setCommercialName("Chevrolet");
		vehicle2.setVinNumber("987654321");
		vehicle2.setCapacity(4);

		Vehicle vehicle3 = new Vehicle();
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
	public void whenFindAllVehiclesThenOK() {

		Mockito.when(vehicleRepository.findAll()).thenReturn(vehiclesMock);

		List<Vehicle> vehicles = vehicleService.listAllVehicles();

		Assertions.assertNotNull(vehicles);
		assertEquals(3, vehicles.size());

	}

	@Test
	public void whenCreateVehicleThenOK(){
		//PREPARE
		Vehicle vehicleMock = new Vehicle();
		vehicleMock.setPlate("AAA-111");
		vehicleMock.setMake("Ford");
		vehicleMock.setManufacturer("Ford");
		vehicleMock.setCommercialName("Ford");
		vehicleMock.setVinNumber("123456789");
		vehicleMock.setCapacity(4);

		Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(vehicleMock);

		//USE
		Vehicle vehicleCreated = vehicleService.createVehicle(vehicleMock);

		//ASSERT
		assertEquals(vehicleMock.getPlate(), vehicleCreated.getPlate());
	}

	@Test
	public void whenCreateVehicleListThenOK(){
		//PREPARE
		List<Vehicle> vehiclesMock = this.vehiclesMock;

		Mockito.when(vehicleRepository.saveAll(Mockito.any(List.class))).thenReturn(vehiclesMock);

		//USE
		List<Vehicle> vehiclesCreated = vehicleService.createVehicles(this.vehiclesMock);

		//ASSERT
		assertEquals(vehiclesMock.size(), vehiclesCreated.size());
		assertEquals(vehiclesMock.get(0).getPlate(), vehiclesCreated.get(0).getPlate());
		assertEquals(vehiclesMock.get(1).getPlate(), vehiclesCreated.get(1).getPlate());
		assertEquals(vehiclesMock.get(2).getPlate(), vehiclesCreated.get(2).getPlate());
	}

	@Test
	void whenUpdateVehicleThenKo()
	{
		//Prepare
		Mockito.when(vehicleRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);

		//Use
		Exception exception = Assertions.assertThrows(Exception.class, () -> { vehicleService.updateVehicle(new Vehicle()); }
		);

		//Assert
		assertEquals("Vehicle does not exist", exception.getMessage());
	}

	@Test
	void whenUpdateVehicleThenOK() throws Exception
	{
		//Prepare
		Vehicle vehicleUpdatedMock = new Vehicle();
		vehicleUpdatedMock.setId(1);
		vehicleUpdatedMock.setPlate("AAA-111");
		vehicleUpdatedMock.setMake("Ford");
		vehicleUpdatedMock.setManufacturer("Ford");
		vehicleUpdatedMock.setCommercialName("Ford");
		vehicleUpdatedMock.setVinNumber("123456789");
		vehicleUpdatedMock.setCapacity(4);

		Vehicle vehicleMock = vehiclesMock.get(0);

		Mockito.when(vehicleRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		Mockito.when(vehicleRepository.getOne(Mockito.anyInt())).thenReturn(vehicleMock);
		Mockito.when(vehicleRepository.saveAndFlush(Mockito.any(Vehicle.class))).thenReturn(vehicleUpdatedMock);
		//Use

		Vehicle vehicleUpdated = vehicleService.updateVehicle(vehicleUpdatedMock);

		//Assert
		assertEquals(vehicleUpdatedMock.getPlate(), vehicleUpdated.getPlate());
	}

	@Test
	public void whenDeleteByIdThenOK() throws Exception
	{
		Mockito.when(vehicleRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);

		vehicleService.deleteVehicleById(1);
		Mockito.verify(vehicleRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
	}

	@Test
	public void whenDeleteByIdThenKO() throws Exception
	{
		Mockito.when(vehicleRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);

		Exception exception = Assertions.assertThrows(Exception.class, () -> { vehicleService.deleteVehicleById(1);});

		assertEquals("Vehicle does not exist", exception.getMessage());
	}

	@Test
	public void whenDuplicateVehicleThenOK() throws Exception
	{
		Vehicle vehicleMock = this.vehiclesMock.get(0);
		vehicleMock.setId(1);

		Vehicle vehicleDuplicatedMock = vehicleMock;
		vehicleDuplicatedMock.setId(null);
		vehicleDuplicatedMock.setPlate("AAA-111");

		Mockito.when(vehicleRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(vehicleMock);

		Vehicle vehicleDuplicated = vehicleService.duplicateVehicle(vehicleDuplicatedMock, 1);

		assertEquals(vehicleMock.getId(), vehicleDuplicated.getId());
		assertEquals(vehicleMock.getMake(), vehicleDuplicated.getMake());

	}


	@Test
	public void whenDuplicateVehicleThenKO() throws Exception
	{
		Mockito.when(vehicleRepository.existsById(Mockito.anyInt())).thenReturn(Boolean.FALSE);

		Exception exception = Assertions.assertThrows(Exception.class, () -> { vehicleService.duplicateVehicle(new Vehicle(),1);});

		assertEquals("Can't be cloned because don't exist", exception.getMessage());
	}

}