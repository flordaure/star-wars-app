package com.conexaproject.star_wars_app.mappers;

import com.conexaproject.star_wars_app.dto.films.FilmsResult;
import com.conexaproject.star_wars_app.model.films.Film;

public class FilmMapper {

    public static Film getFilmData(FilmsResult filmResult) {
        return Film.builder()
                .title(filmResult.getProperties().getTitle())
                .openingCrawl(filmResult.getProperties().getOpeningCrawl())
                .director(filmResult.getProperties().getDirector())
                .characters(filmResult.getProperties().getCharacters())
                .created(filmResult.getProperties().getCreated())
                .edited(filmResult.getProperties().getEdited())
                .episodeId(filmResult.getProperties().getEpisodeId())
                .producer(filmResult.getProperties().getProducer())
                .releaseDate(filmResult.getProperties().getReleaseDate())
                .starships(filmResult.getProperties().getStarships())
                .uid(filmResult.getUid())
                .url(filmResult.getProperties().getUrl())
                .vehicles(filmResult.getProperties().getVehicles())
                .build();
    }
}
