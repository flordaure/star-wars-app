package com.conexaproject.star_wars_app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicInformation {

    @Schema(description = "A unique identifier for the character", example = "1")
    private String uid;

    @Schema(description = "The name of the character", example = "Luke Skywalker")
    private String name;

    @Schema(description = "The URL of the character's resource", example = "https://www.swapi.tech/api/people/1")
    private String url;

    @Override
    public String toString() {
        return "CharacterBasicData{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
