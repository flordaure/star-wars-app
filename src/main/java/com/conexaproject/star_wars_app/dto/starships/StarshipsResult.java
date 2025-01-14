package com.conexaproject.star_wars_app.dto.starships;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StarshipsResult {

    private StarshipsProperties properties;
    private String description;
    @JsonProperty("_id")
    private String id;
    private String uid;
    @JsonProperty("__v")
    private Integer v;

}
