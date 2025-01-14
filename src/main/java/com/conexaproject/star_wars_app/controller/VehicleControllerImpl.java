package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.exceptions.FormatException;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.vehicles.Vehicle;
import com.conexaproject.star_wars_app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.conexaproject.star_wars_app.utility.Errors.*;

@RestController
public class VehicleControllerImpl extends ApiController implements VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Override
    public ResponseEntity<List<BasicInformation>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @Override
    public ResponseEntity<List<BasicInformation>> getVehiclesFromPages(List<Integer> pages) {
        if (pages.isEmpty()) {
            throw new FormatException(PAGES_PARAM_IS_NULL.getCode(), PAGES_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(vehicleService.getVehiclesOfSpecificPages(pages));
    }

    @Override
    public ResponseEntity<Vehicle> getVehicleById(Integer id) {
        if (id == null) {
            throw new FormatException(ID_PARAM_IS_NULL.getCode(), ID_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @Override
    public ResponseEntity<List<Vehicle>> getVehiclesByName(String name) {
        if (name == null) {
            throw new FormatException(NAME_PARAM_IS_NULL.getCode(), NAME_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(vehicleService.getVehiclesByName(name));
    }
}
