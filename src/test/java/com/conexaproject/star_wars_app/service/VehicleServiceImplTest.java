package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.vehicles.VehiclesByIdResponse;
import com.conexaproject.star_wars_app.dto.vehicles.VehiclesByNameResponse;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.vehicles.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static com.conexaproject.star_wars_app.Util.loadJsonFromFile;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VehicleServiceImplTest {

    @InjectMocks
    VehicleServiceImpl vehicleService;

    @Mock
    ApiService apiService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(vehicleService, "vehicleEndpoint", "/vehicles");
    }

    @Test
    public void getAllVehicle() throws Exception {

        String vehicleJson = loadJsonFromFile("mock_all_vehicle.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BasicDataResponse allVehicleMock = objectMapper.readValue(vehicleJson, BasicDataResponse.class);

        Mockito.when(apiService.callApi("/vehicles?page=1&limit=10", BasicDataResponse.class)).thenReturn(new ResponseEntity<>(allVehicleMock, HttpStatus.OK));

        List<BasicInformation> vehicles = vehicleService.getAllVehicles();
        Mockito.verify(apiService, Mockito.times(1)).callApi("/vehicles?page=1&limit=10", BasicDataResponse.class);
        Assertions.assertNotNull(vehicles);
        Assertions.assertFalse(vehicles.isEmpty());
        Assertions.assertEquals(10, vehicles.size());

    }

    @Test
    public void getVehicleFromSpecificPage() throws Exception {

        String vehicleJson = loadJsonFromFile("mock_all_vehicle.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BasicDataResponse allVehicleMock = objectMapper.readValue(vehicleJson, BasicDataResponse.class);

        Mockito.when(apiService.callApi("/vehicles?page=1&limit=10", BasicDataResponse.class)).thenReturn(new ResponseEntity<>(allVehicleMock, HttpStatus.OK));

        List<BasicInformation> vehicles = vehicleService.getVehiclesOfSpecificPages(Collections.singletonList(1));
        Mockito.verify(apiService, Mockito.times(1)).callApi("/vehicles?page=1&limit=10", BasicDataResponse.class);
        Assertions.assertNotNull(vehicles);
        Assertions.assertFalse(vehicles.isEmpty());
        Assertions.assertEquals(10, vehicles.size());

    }

    @Test
    public void getVehicleById() throws Exception {

        String vehicleJson = loadJsonFromFile("mock_one_vehicle.json");
        ObjectMapper objectMapper = new ObjectMapper();

        VehiclesByIdResponse vehicleMock = objectMapper.readValue(vehicleJson, VehiclesByIdResponse.class);

        Mockito.when(apiService.callApi("/vehicles/7", VehiclesByIdResponse.class)).thenReturn(new ResponseEntity<>(vehicleMock, HttpStatus.OK));

        Vehicle vehicle = vehicleService.getVehicleById(7);
        Mockito.verify(apiService, Mockito.times(1)).callApi("/vehicles/7", VehiclesByIdResponse.class);
        Assertions.assertNotNull(vehicle);
        Assertions.assertEquals("7", vehicle.getUid());
        Assertions.assertEquals("X-34 landspeeder", vehicle.getName());

    }

    @Test
    public void getVehicleByName() throws Exception {

        String vehicleJson = loadJsonFromFile("mock_vehicle_by_name.json");
        ObjectMapper objectMapper = new ObjectMapper();

        VehiclesByNameResponse vehicleMock = objectMapper.readValue(vehicleJson, VehiclesByNameResponse.class);

        Mockito.when(apiService.callApi("/vehicles?name=X-34 landspeeder", VehiclesByNameResponse.class)).thenReturn(new ResponseEntity<>(vehicleMock, HttpStatus.OK));

        List<Vehicle> vehicles = vehicleService.getVehiclesByName("X-34 landspeeder");
        Mockito.verify(apiService, Mockito.times(1)).callApi("/vehicles?name=X-34 landspeeder", VehiclesByNameResponse.class);
        Assertions.assertFalse(vehicles.isEmpty());
        Assertions.assertEquals(1, vehicles.size());
        Assertions.assertEquals("7", vehicles.get(0).getUid());
        Assertions.assertEquals("X-34 landspeeder", vehicles.get(0).getName());

    }


}