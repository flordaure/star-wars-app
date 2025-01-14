package com.conexaproject.star_wars_app.model.starships;

import com.conexaproject.star_wars_app.model.BasicInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Starship extends BasicInformation {

    @Schema(description = "The model of the starship", example = "Imperial I-class Star Destroyer")
    private String model;

    @Schema(description = "The class of the starship", example = "Star Destroyer")
    private String starshipClass;

    @Schema(description = "The manufacturer of the starship", example = "Kuat Drive Yards")
    private String manufacturer;

    @Schema(description = "The cost of the starship in credits", example = "150000000")
    private String costInCredits;

    @Schema(description = "The length of the starship in meters", example = "1600")
    private String length;

    @Schema(description = "The crew required to operate the starship", example = "47,060")
    private String crew;

    @Schema(description = "The number of passengers the starship can carry", example = "n/a")
    private String passengers;

    @Schema(description = "The maximum atmospheric speed of the starship", example = "975")
    private String maxAtmospheringSpeed;

    @Schema(description = "The hyperdrive rating of the starship", example = "2.0")
    private String hyperdriveRating;

    @Schema(description = "The MGLT of the starship", example = "60")
    private String mglt;

    @Schema(description = "The cargo capacity of the starship in kilograms", example = "36000000")
    private String cargoCapacity;

    @Schema(description = "The consumables for the starship", example = "2 years")
    private String consumables;

    @Schema(description = "The list of pilots who can operate the starship", example = "[]")
    private List<String> pilots;

    @Schema(description = "The date the starship record was created", example = "2020-09-17T17:55:06.604Z")
    private String created;

    @Schema(description = "The date the starship record was last edited", example = "2020-09-17T17:55:06.604Z")
    private String edited;


    public Starship(String uid, String name, String url, String model, String starshipClass, String manufacturer, String costInCredits, String length, String crew, String passengers, String maxAtmospheringSpeed, String hyperdriveRating, String mglt, String cargoCapacity, String consumables, List<String> pilots, String created, String edited) {
        super(uid, name, url);
        this.model = model;
        this.starshipClass = starshipClass;
        this.manufacturer = manufacturer;
        this.costInCredits = costInCredits;
        this.length = length;
        this.crew = crew;
        this.passengers = passengers;
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
        this.hyperdriveRating = hyperdriveRating;
        this.mglt = mglt;
        this.cargoCapacity = cargoCapacity;
        this.consumables = consumables;
        this.pilots = pilots;
        this.created = created;
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "StarshipsCompleteData{" +
                "model='" + model + '\'' +
                ", starshipClass='" + starshipClass + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", costInCredits='" + costInCredits + '\'' +
                ", length='" + length + '\'' +
                ", crew='" + crew + '\'' +
                ", passengers='" + passengers + '\'' +
                ", maxAtmospheringSpeed='" + maxAtmospheringSpeed + '\'' +
                ", hyperdriveRating='" + hyperdriveRating + '\'' +
                ", mglt='" + mglt + '\'' +
                ", cargoCapacity='" + cargoCapacity + '\'' +
                ", consumables='" + consumables + '\'' +
                ", pilots=" + pilots +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                "} " + super.toString();
    }
}
