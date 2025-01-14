package com.conexaproject.star_wars_app.model.films;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Film {

    @Schema(description = "The title of the film", example = "Return of the Jedi")
    private String title;

    @Schema(description = "The opening crawl text of the film", example = "Luke Skywalker has returned to\nhis home planet of Tatooine in\nan attempt to rescue his\nfriend Han Solo from the\nclutches of the vile gangster\nJabba the Hutt.")
    private String openingCrawl;

    @Schema(description = "The director of the film", example = "Richard Marquand")
    private String director;

    @Schema(description = "A list of character URLs featured in the film", example = "[\"https://www.swapi.tech/api/people/1\", \"https://www.swapi.tech/api/people/2\"]")
    private List<String> characters;

    @Schema(description = "A list of starship URLs featured in the film", example = "[\"https://www.swapi.tech/api/starships/2\", \"https://www.swapi.tech/api/starships/3\"]")
    private List<String> starships;

    @Schema(description = "A list of vehicle URLs featured in the film", example = "[\"https://www.swapi.tech/api/vehicles/8\", \"https://www.swapi.tech/api/vehicles/16\"]")
    private List<String> vehicles;

    @Schema(description = "The date and time when the film record was created", example = "2025-01-13T03:40:52.546Z")
    private String created;

    @Schema(description = "The date and time when the film record was last edited", example = "2025-01-13T03:40:52.546Z")
    private String edited;

    @Schema(description = "The producers of the film", example = "Howard G. Kazanjian, George Lucas, Rick McCallum")
    private String producer;

    @Schema(description = "The episode ID of the film", example = "6")
    private String episodeId;

    @Schema(description = "The release date of the film", example = "1983-05-25")
    private String releaseDate;

    @Schema(description = "The URL of the film's resource", example = "https://www.swapi.tech/api/films/3")
    private String url;

    @Schema(description = "A unique identifier for the film", example = "3")
    private String uid;


    @Override
    public String toString() {
        return "FilmsCompleteData{" +
                "characters=" + characters +
                ", starships=" + starships +
                ", vehicles=" + vehicles +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", producer='" + producer + '\'' +
                ", title='" + title + '\'' +
                ", episodeId='" + episodeId + '\'' +
                ", director='" + director + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", url='" + url + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
