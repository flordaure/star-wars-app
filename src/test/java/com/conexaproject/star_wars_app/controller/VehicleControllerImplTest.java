package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.vehicles.VehiclesByIdResponse;
import com.conexaproject.star_wars_app.dto.vehicles.VehiclesByNameResponse;
import com.conexaproject.star_wars_app.mappers.BasicDataMapper;
import com.conexaproject.star_wars_app.mappers.VehicleMapper;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.vehicles.Vehicle;
import com.conexaproject.star_wars_app.service.VehicleServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.conexaproject.star_wars_app.Util.loadJsonFromFile;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleControllerImplTest {

    @InjectMocks
    VehicleControllerImpl vehicleController;

    @Mock
    VehicleServiceImpl vehicleService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllVehiclesSuccess() throws IOException {

        BasicDataResponse vehicleResponse = objectMapper.readValue(loadJsonFromFile("mock_all_vehicle.json"), BasicDataResponse.class);
        List<BasicInformation> vehicles = vehicleResponse.getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList());
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        ResponseEntity<List<BasicInformation>> vehicleResponseEntity = vehicleController.getAllVehicles();
        Assertions.assertNotNull(vehicleResponseEntity);
        Assertions.assertNotNull(vehicleResponseEntity.getBody());
        Assertions.assertFalse(vehicleResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(10, vehicleResponseEntity.getBody().size());
        Assertions.assertEquals(vehicles.get(0).getName(), vehicleResponseEntity.getBody().get(0).getName());
    }

    @Test
    public void getVehiclesFromSpecificPagesSuccess() throws IOException {

        BasicDataResponse vehicleResponse = objectMapper.readValue(loadJsonFromFile("mock_all_vehicle.json"), BasicDataResponse.class);
        List<BasicInformation> vehicles = vehicleResponse.getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList());
        when(vehicleService.getVehiclesOfSpecificPages(Collections.singletonList(1))).thenReturn(vehicles);

        ResponseEntity<List<BasicInformation>> vehiclesResponseEntity = vehicleController.getVehiclesFromPages(Collections.singletonList(1));
        Assertions.assertNotNull(vehiclesResponseEntity);
        Assertions.assertNotNull(vehiclesResponseEntity.getBody());
        Assertions.assertFalse(vehiclesResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(10, vehiclesResponseEntity.getBody().size());
        Assertions.assertEquals(vehicles.get(0).getName(), vehiclesResponseEntity.getBody().get(0).getName());
    }

    @Test
    public void getVehicleByIdSuccess() throws IOException {
        VehiclesByIdResponse vehiclesById = objectMapper.readValue(loadJsonFromFile("mock_one_vehicle.json"), VehiclesByIdResponse.class);
        Vehicle vehicle = VehicleMapper.getVehicleData(vehiclesById.getResult());
        when(vehicleService.getVehicleById(7)).thenReturn(vehicle);
        ResponseEntity<Vehicle> vehiclesResponseEntity = vehicleController.getVehicleById(7);
        Assertions.assertNotNull(vehiclesResponseEntity);
        Assertions.assertNotNull(vehiclesResponseEntity.getBody());
        Assertions.assertEquals(vehicle.getName(), vehiclesResponseEntity.getBody().getName());
    }

    @Test
    public void getVehicleByNameSuccess() throws IOException {
        VehiclesByNameResponse vehicleByNameResponse = objectMapper.readValue(loadJsonFromFile("mock_vehicle_by_name.json"),  VehiclesByNameResponse.class);
        List<Vehicle> vehicles = vehicleByNameResponse.getResult().stream().map(VehicleMapper::getVehicleData).collect(Collectors.toList());
        when(vehicleService.getVehiclesByName("SX-34 landspeeder")).thenReturn(vehicles);
        ResponseEntity<List<Vehicle>> vehiclesResponseEntity = vehicleController.getVehiclesByName("SX-34 landspeeder");
        Assertions.assertNotNull(vehiclesResponseEntity);
        Assertions.assertNotNull(vehiclesResponseEntity.getBody());
        Assertions.assertFalse(vehiclesResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(vehicles.get(0).getName(), vehiclesResponseEntity.getBody().get(0).getName());
    }


}