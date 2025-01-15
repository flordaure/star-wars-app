package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.dto.films.FilmsByIdResponse;
import com.conexaproject.star_wars_app.dto.films.FilmsResponse;
import com.conexaproject.star_wars_app.mappers.FilmMapper;
import com.conexaproject.star_wars_app.model.films.Film;
import com.conexaproject.star_wars_app.service.FilmsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.conexaproject.star_wars_app.Util.loadJsonFromFile;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilmControllerImplTest {

    @InjectMocks
    FilmControllerImpl filmController;

    @Mock
    FilmsServiceImpl filmsService;

    private FilmsResponse filmResponse;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllFilmsSuccess() throws IOException {

        filmResponse = objectMapper.readValue(loadJsonFromFile("mock_all_films.json"), FilmsResponse.class);
        List<Film> filmList = filmResponse.getResult().stream().map(FilmMapper::getFilmData).collect(Collectors.toList());
        when(filmsService.getAllFilms()).thenReturn(filmList);
        ResponseEntity<List<Film>> filmsResponseEntity = filmController.getAllFilms();

        Assertions.assertNotNull(filmsResponseEntity);
        Assertions.assertNotNull(filmsResponseEntity.getBody());
        Assertions.assertFalse(filmsResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(filmList.get(0).getTitle(), filmsResponseEntity.getBody().get(0).getTitle());


    }

    @Test
    public void getFilmByIdSuccess() throws IOException {

        FilmsByIdResponse filmResponse = objectMapper.readValue(loadJsonFromFile("mock_one_film.json"), FilmsByIdResponse.class);
        Film film = FilmMapper.getFilmData(filmResponse.getResult());
        when(filmsService.getFilmsById(1)).thenReturn(film);
        ResponseEntity<Film> filmsResponseEntity = filmController.getFilmsById(1);

        Assertions.assertNotNull(filmsResponseEntity);
        Assertions.assertNotNull(filmsResponseEntity.getBody());
        Assertions.assertEquals(film.getTitle(), filmsResponseEntity.getBody().getTitle());


    }

    @Test
    public void getFilmsByTitleSuccess() throws IOException {

        filmResponse = objectMapper.readValue(loadJsonFromFile("mock_film_by_name.json"), FilmsResponse.class);
        List<Film> filmList = filmResponse.getResult().stream().map(FilmMapper::getFilmData).collect(Collectors.toList());
        when(filmsService.getFilmsByTitle("A New Hope")).thenReturn(filmList);
        ResponseEntity<List<Film>> filmsResponseEntity = filmController.getFilmsByTitle("A New Hope");

        Assertions.assertNotNull(filmsResponseEntity);
        Assertions.assertNotNull(filmsResponseEntity.getBody());
        Assertions.assertFalse(filmsResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(filmList.get(0).getTitle(), filmsResponseEntity.getBody().get(0).getTitle());


    }
}