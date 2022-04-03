package com.idiada.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.idiada.exercise.domain.Vehicle;
import com.idiada.exercise.services.VehicleService;


public class DBInitializer implements ServletContextListener {

	@Autowired
	private  VehicleService vehicleService;
	
	private static final String[] MAKES = new String[] {"Ford", "Audi", "Mercedes", "SEAT", "Alfa Romeo"};
	private static final String[] MANUFACTURERS = new String[] {"DAIMLER AG", "VOLKSWAGEN AG", "VOLVO Group", "Fiat Auto SpA"};
	private static final String[] COMMERCIAL_NAMES = new String[] {"Focus", "Ibiza", "Astra", "Corsa", "Insignia", "Punto"};


	public void contextInitialized(ServletContextEvent arg0) {

		List<Vehicle> vehiclesList = new ArrayList<Vehicle>();
		// init DB with vehicles
		for (int i=0; i<25; i++){
			vehiclesList.add(simulateVehicles(i));
		}
		vehicleService.createVehicles(vehiclesList);
	}

	private Vehicle simulateVehicles(int index){
		Vehicle vehicle = new Vehicle();
		vehicle.setCapacity((int)(Math.random()*1000));
		vehicle.setCommercialName(getRandomString(COMMERCIAL_NAMES));
		vehicle.setMake(getRandomString(MAKES));
		vehicle.setManufacturer(getRandomString(MANUFACTURERS));
		vehicle.setPlate(generateRandomPlate());
		vehicle.setVinNumber("vinNumber"+index);

		return vehicle;
	}

	public static String generateRandomPlate () {
		String numbers = "0123456789";
		String letters = "BCDFGHJKLMNPQRSTVWXYZ";
		
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i=0;i<4;i++) {
			sb.append(numbers.charAt(r.nextInt(numbers.length())));
		}
		sb.append("-");
		for (int i=0;i<3;i++) {
			sb.append(letters.charAt(r.nextInt(letters.length())));
		}
		
		return sb.toString();
	}

	public static String getRandomString(String[] input) {
		if (input == null || input.length < 0) {
			throw new IllegalArgumentException("Bad input string");
		}
		Random r = new Random();
		return input[r.nextInt(input.length)];
	}
}
