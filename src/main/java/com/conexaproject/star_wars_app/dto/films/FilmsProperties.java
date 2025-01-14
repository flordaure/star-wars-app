package com.conexaproject.star_wars_app.dto.films;

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
public class FilmsProperties {

    private List<String> characters;
    private List<String> planets;
    private List<String> starships;
    private List<String> vehicles;
    private List<String> species;
    private String created;
    private String edited;
    private String producer;
    private String title;
    @JsonProperty("episode_id")
    private String episodeId;
    private String director;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("opening_crawl")
    private String openingCrawl;
    private String url;
}
