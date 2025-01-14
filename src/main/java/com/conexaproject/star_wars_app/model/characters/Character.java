package com.conexaproject.star_wars_app.model.characters;

import com.conexaproject.star_wars_app.model.BasicInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Character extends BasicInformation {

    @Schema(description = "The height of the character in centimeters", example = "172")
    private String height;

    @Schema(description = "The mass of the character in kilograms", example = "77")
    private String mass;

    @Schema(description = "The hair color of the character", example = "blond")
    private String hairColor;

    @Schema(description = "The skin color of the character", example = "fair")
    private String skinColor;

    @Schema(description = "The eye color of the character", example = "blue")
    private String eyeColor;

    @Schema(description = "The birth year of the character", example = "19BBY")
    private String birthYear;

    @Schema(description = "The gender of the character", example = "male")
    private String gender;

    @Schema(description = "The date and time when the character record was created", example = "2025-01-13T03:40:52.556Z")
    private String created;

    @Schema(description = "The date and time when the character record was last edited", example = "2025-01-13T03:40:52.556Z")
    private String edited;

    @Schema(description = "The URL of the character's homeworld planet", example = "https://www.swapi.tech/api/planets/1")
    private String homeworld;

    public Character(String uid, String name, String url, String height, String mass, String hairColor, String skinColor,
                     String eyeColor, String birthYear, String gender, String created, String edited, String homeworld) {
        super(uid, name, url);
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.birthYear = birthYear;
        this.gender = gender;
        this.created = created;
        this.edited = edited;
        this.homeworld = homeworld;
    }

    @Override
    public String toString() {
        return "CharacterCompleteData{" +
                "height='" + height + '\'' +
                ", mass='" + mass + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender='" + gender + '\'' +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", homeworld='" + homeworld + '\'' +
                "} " + super.toString();
    }
}
