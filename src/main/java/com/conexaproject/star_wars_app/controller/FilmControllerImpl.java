package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.exceptions.FormatException;
import com.conexaproject.star_wars_app.model.films.Film;
import com.conexaproject.star_wars_app.service.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.conexaproject.star_wars_app.utility.Errors.ID_PARAM_IS_NULL;

@RestController
public class FilmControllerImpl extends ApiController implements FilmController{

    @Autowired
    private FilmsService filmsService;

    @Override
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.ok(filmsService.getAllFilms());
    }
    @Override
    public ResponseEntity<Film> getFilmsById(Integer id){
        if (id == null) {
            throw new FormatException(ID_PARAM_IS_NULL.getCode(), ID_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(filmsService.getFilmsById(id));
    }

    @Override
    public ResponseEntity<List<Film>> getFilmsByTitle(@RequestParam String title){
        return ResponseEntity.ok(filmsService.getFilmsByTitle(title));
    }
}
