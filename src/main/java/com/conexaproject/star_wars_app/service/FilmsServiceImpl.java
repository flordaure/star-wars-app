package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.dto.films.FilmsByIdResponse;
import com.conexaproject.star_wars_app.dto.films.FilmsResponse;
import com.conexaproject.star_wars_app.exceptions.DataException;
import com.conexaproject.star_wars_app.exceptions.ServiceException;
import com.conexaproject.star_wars_app.mappers.FilmMapper;
import com.conexaproject.star_wars_app.model.films.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.conexaproject.star_wars_app.mappers.FilmMapper.getFilmData;
import static com.conexaproject.star_wars_app.utility.Errors.*;

@Service
public class FilmsServiceImpl implements FilmsService {

    @Value("${films.endpoint}")
    private String filmsEndpoint;

    @Autowired
    private ApiService apiService;

    @Cacheable("films")
    @Override
    public List<Film> getAllFilms() {
        List<Film> completeData = new ArrayList<>();
        String url = filmsEndpoint;

        ResponseEntity<FilmsResponse> filmResponse = apiService.callApi(url, FilmsResponse.class);
        if (filmResponse.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), filmResponse.getStatusCode()));
        }
        if (filmResponse.getBody() != null && !filmResponse.getBody().getResult().isEmpty()) {

            completeData = filmResponse.getBody().getResult().stream().map(FilmMapper::getFilmData).collect(Collectors.toList());
        }

        return completeData;
    }


    @Override
    public Film getFilmsById(Integer id) {
        String url = filmsEndpoint + "/" + id;
        Film film = null;

        ResponseEntity<FilmsByIdResponse> filmById = apiService.callApi(url, FilmsByIdResponse.class);
        if (filmById.getStatusCode() != HttpStatus.OK && filmById.getStatusCode() != HttpStatus.NOT_FOUND) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), filmById.getStatusCode()));
        }
        if (filmById.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new DataException(ID_NOT_FOUND.getCode(), String.format(ID_NOT_FOUND.getMessage(), id));
        }
        if (filmById.getBody() != null && filmById.getBody().getResult() != null) {
            film = getFilmData(filmById.getBody().getResult());
        }
        return film;
    }

    @Override
    public List<Film> getFilmsByTitle(String title) {

        List<Film> completeData = new ArrayList<>();
        String url = filmsEndpoint + "?title=" + title;
        ResponseEntity<FilmsResponse> filmByTitle = apiService.callApi(url, FilmsResponse.class);

        if (filmByTitle.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), filmByTitle.getStatusCode()));
        }

        if (filmByTitle.getBody() != null && filmByTitle.getBody().getResult().isEmpty()) {
            throw new DataException(NAME_NOT_FOUND.getCode(), String.format(NAME_NOT_FOUND.getMessage(), title));
        }
        if (filmByTitle.getBody() != null && !filmByTitle.getBody().getResult().isEmpty()) {
            completeData = filmByTitle.getBody().getResult().stream().map(FilmMapper::getFilmData).collect(Collectors.toList());
        }
        return completeData;

    }

}


