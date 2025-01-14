package com.conexaproject.star_wars_app.dto.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiclesProperties {

    private String model;
    @JsonProperty("vehicle_class")
    private String vehicleClass;
    private String manufacturer;
    @JsonProperty("cost_in_credits")
    private String costInCredits;
    private String length;
    private String crew;
    private String passengers;
    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;
    @JsonProperty("cargo_capacity")
    private String cargoCapacity;
    private String consumables;
    private List<String> films;
    private List<String> pilots;
    private String created;
    private String edited;
    private String name;
    private String url;
}
