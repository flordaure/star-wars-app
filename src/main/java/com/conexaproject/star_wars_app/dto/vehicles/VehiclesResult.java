package com.conexaproject.star_wars_app.dto.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiclesResult {

    private VehiclesProperties properties;
    private String description;
    @JsonProperty("_id")
    private String id;
    private String uid;
    @JsonProperty("__v")
    private Integer v;

}
