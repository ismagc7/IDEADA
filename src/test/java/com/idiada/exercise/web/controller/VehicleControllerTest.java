package com.idiada.exercise.web.controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.idiada.exercise.dto.CreateVehicleDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllVehicles() throws Exception {

        MockHttpServletResponse response = this.mockMvc.perform(MockMvcRequestBuilders.get("/vehicles").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void createVehicle() throws Exception {

        CreateVehicleDTO vehicleToCreate = new CreateVehicleDTO();
        vehicleToCreate.setPlate("1212-AAA");
        vehicleToCreate.setManufacturer("FOO");
        vehicleToCreate.setMake("FOO");
        vehicleToCreate.setCommercialName("Foo");
        vehicleToCreate.setVinNumber("fooVinNumber");
        vehicleToCreate.setCapacity(233);

        String inputJson = new ObjectMapper().writeValueAsString(vehicleToCreate);

        MockHttpServletResponse response = this.mockMvc.perform(MockMvcRequestBuilders.post("/vehicles",inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void createVehicleList() {
    }

    @Test
    void deleteVehicle() {
    }

    @Test
    void updateVehicle() {
    }

    @Test
    void cloneVehicle() {
    }
}