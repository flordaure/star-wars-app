package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.dto.films.FilmsByIdResponse;
import com.conexaproject.star_wars_app.dto.films.FilmsResponse;
import com.conexaproject.star_wars_app.model.films.Film;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.conexaproject.star_wars_app.Util.loadJsonFromFile;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FilmsServiceImplTest {

    @InjectMocks
    FilmsServiceImpl filmsService;

    @Mock
    ApiService apiService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(filmsService, "filmsEndpoint", "/films");
    }

    @Test
    public void getAllFilms() throws Exception {

        String filmsJson = loadJsonFromFile("mock_all_films.json");
        ObjectMapper objectMapper = new ObjectMapper();

        FilmsResponse allFilmsMock = objectMapper.readValue(filmsJson, FilmsResponse.class);

        Mockito.when(apiService.callApi("/films", FilmsResponse.class)).thenReturn(new ResponseEntity<>(allFilmsMock, HttpStatus.OK));

        List<Film> films = filmsService.getAllFilms();
        Mockito.verify(apiService, Mockito.times(1)).callApi("/films", FilmsResponse.class);
        Assertions.assertNotNull(films);
        Assertions.assertFalse(films.isEmpty());
        Assertions.assertEquals(6, films.size());

    }

    @Test
    public void getFilmsById() throws Exception {

        String filmsJson = loadJsonFromFile("mock_one_film.json");
        ObjectMapper objectMapper = new ObjectMapper();

        FilmsByIdResponse filmsMock = objectMapper.readValue(filmsJson, FilmsByIdResponse.class);

        Mockito.when(apiService.callApi("/films/1", FilmsByIdResponse.class)).thenReturn(new ResponseEntity<>(filmsMock, HttpStatus.OK));

        Film film = filmsService.getFilmsById(1);
        Mockito.verify(apiService, Mockito.times(1)).callApi("/films/1", FilmsByIdResponse.class);
        Assertions.assertNotNull(film);
        Assertions.assertEquals("1", film.getUid());
        Assertions.assertEquals("A New Hope", film.getTitle());

    }

    @Test
    public void getFilmsByName() throws Exception {

        String filmJson = loadJsonFromFile("mock_film_by_name.json");
        ObjectMapper objectMapper = new ObjectMapper();

        FilmsResponse filmsMock = objectMapper.readValue(filmJson, FilmsResponse.class);

        Mockito.when(apiService.callApi("/films?title=A New Hope", FilmsResponse.class)).thenReturn(new ResponseEntity<>(filmsMock, HttpStatus.OK));

        List<Film> films = filmsService.getFilmsByTitle("A New Hope");
        Mockito.verify(apiService, Mockito.times(1)).callApi("/films?title=A New Hope", FilmsResponse.class);
        Assertions.assertFalse(films.isEmpty());
        Assertions.assertEquals(1, films.size());
        Assertions.assertEquals("1", films.get(0).getUid());
        Assertions.assertEquals("A New Hope", films.get(0).getTitle());

    }

}