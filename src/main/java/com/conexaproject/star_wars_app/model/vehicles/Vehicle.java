package com.conexaproject.star_wars_app.model.vehicles;

import com.conexaproject.star_wars_app.model.BasicInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Vehicle extends BasicInformation {


    @Schema(description = "The model of the vehicle", example = "Modified Luxury Sail Barge")
    private String model;

    @Schema(description = "The class of the vehicle, such as sail barge", example = "sail barge")
    private String vehicleClass;

    @Schema(description = "The manufacturer of the vehicle", example = "Ubrikkian Industries Custom Vehicle Division")
    private String manufacturer;

    @Schema(description = "The cost of the vehicle in credits", example = "285000")
    private String costInCredits;

    @Schema(description = "The length of the vehicle in meters", example = "30")
    private String length;

    @Schema(description = "The number of crew members required to operate the vehicle", example = "26")
    private String crew;

    @Schema(description = "The number of passengers the vehicle can carry", example = "500")
    private String passengers;

    @Schema(description = "The maximum atmospheric speed of the vehicle", example = "100")
    private String maxAtmospheringSpeed;

    @Schema(description = "The cargo capacity of the vehicle in kilograms", example = "2000000")
    private String cargoCapacity;

    @Schema(description = "The consumables needed for the vehicle's operation", example = "Live food tanks")
    private String consumables;

    @Schema(description = "The list of films the vehicle appeared in", example = "[]")
    private List<String> films;

    @Schema(description = "The list of pilots who operate the vehicle", example = "[]")
    private List<String> pilots;

    @Schema(description = "The creation date of the vehicle's record", example = "2020-09-17T17:46:31.415Z")
    private String created;

    @Schema(description = "The last edit date of the vehicle's record", example = "2020-09-17T17:46:31.415Z")
    private String edited;


    public Vehicle(String uid, String name, String url, String model, String vehicleClass, String manufacturer, String costInCredits, String length, String crew, String passengers, String maxAtmospheringSpeed, String cargoCapacity, String consumables, List<String> films, List<String> pilots, String created, String edited) {
        super(uid, name, url);
        this.model = model;
        this.vehicleClass = vehicleClass;
        this.manufacturer = manufacturer;
        this.costInCredits = costInCredits;
        this.length = length;
        this.crew = crew;
        this.passengers = passengers;
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
        this.cargoCapacity = cargoCapacity;
        this.consumables = consumables;
        this.films = films;
        this.pilots = pilots;
        this.created = created;
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "VehiclesCompleteData{" +
                "model='" + model + '\'' +
                ", vehicleClass='" + vehicleClass + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", costInCredits='" + costInCredits + '\'' +
                ", length='" + length + '\'' +
                ", crew='" + crew + '\'' +
                ", passengers='" + passengers + '\'' +
                ", maxAtmospheringSpeed='" + maxAtmospheringSpeed + '\'' +
                ", cargoCapacity='" + cargoCapacity + '\'' +
                ", consumables='" + consumables + '\'' +
                ", films=" + films +
                ", pilots=" + pilots +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                "} " + super.toString();
    }
}
