package com.conexaproject.star_wars_app.dto.starships;

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
public class StarshipsProperties {

    private String model;

    @JsonProperty("starship_class")
    private String starshipClass;

    private String manufacturer;

    @JsonProperty("cost_in_credits")
    private String costInCredits;

    private String length;

    private String crew;

    private String passengers;

    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;

    @JsonProperty("hyperdrive_rating")
    private String hyperdriveRating;

    @JsonProperty("MGLT")
    private String mglt;

    @JsonProperty("cargo_capacity")
    private String cargoCapacity;

    private String consumables;

    private List<String> pilots;

    private String created;

    private String edited;

    private String name;

    private String url;
}
