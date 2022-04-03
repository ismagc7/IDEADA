package com.idiada.exercise;

import com.idiada.exercise.web.controller.VehicleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IdiadaExerciseApplicationTests {

	@Autowired
	private VehicleController vehicleController;

	@Test
	void contextLoads() {
		assertThat(vehicleController).isNotNull();
	}

}
