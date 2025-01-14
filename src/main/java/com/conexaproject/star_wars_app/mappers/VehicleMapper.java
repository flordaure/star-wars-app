package com.conexaproject.star_wars_app.mappers;

import com.conexaproject.star_wars_app.dto.vehicles.VehiclesResult;
import com.conexaproject.star_wars_app.model.vehicles.Vehicle;

public class VehicleMapper {

    public static Vehicle getVehicleData(VehiclesResult vehicleResult){
        return new Vehicle(vehicleResult.getUid(),
                vehicleResult.getProperties().getName(),
                vehicleResult.getProperties().getUrl(),
                vehicleResult.getProperties().getModel(),
                vehicleResult.getProperties().getVehicleClass(),
                vehicleResult.getProperties().getManufacturer(),
                vehicleResult.getProperties().getCostInCredits(),
                vehicleResult.getProperties().getLength(),
                vehicleResult.getProperties().getCrew(),
                vehicleResult.getProperties().getPassengers(),
                vehicleResult.getProperties().getMaxAtmospheringSpeed(),
                vehicleResult.getProperties().getCargoCapacity(),
                vehicleResult.getProperties().getConsumables(),
                vehicleResult.getProperties().getFilms(),
                vehicleResult.getProperties().getPilots(),
                vehicleResult.getProperties().getCreated(),
                vehicleResult.getProperties().getEdited());
    }
}
