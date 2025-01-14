package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.vehicles.Vehicle;

import java.util.List;

public interface VehicleService {

    List<BasicInformation> getAllVehicles();
    List<BasicInformation> getVehiclesOfSpecificPages(List<Integer> pagesNumbers);
    Vehicle getVehicleById(Integer id);
    List<Vehicle> getVehiclesByName(String name);
}
