package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.model.films.Film;

import java.util.List;

public interface FilmsService {

    List<Film> getAllFilms();
    Film getFilmsById(Integer id);
    List<Film> getFilmsByTitle(String title);
}
