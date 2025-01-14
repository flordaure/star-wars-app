package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.exceptions.FormatException;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.starships.Starship;
import com.conexaproject.star_wars_app.service.StarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.conexaproject.star_wars_app.utility.Errors.*;

@RestController
public class StarshipControllerImpl extends ApiController implements StarshipController {

    @Autowired
    StarshipService starshipService;

    @Override
    public ResponseEntity<List<BasicInformation>> getAllStarships() {
        return ResponseEntity.ok(starshipService.getAllStarships());
    }

    @Override
    public ResponseEntity<List<BasicInformation>> getStarshipsFromPages(List<Integer> pages) {
        if (pages.isEmpty()) {
            throw new FormatException(PAGES_PARAM_IS_NULL.getCode(), PAGES_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(starshipService.getStarshipsOfSpecificPages(pages));
    }

    @Override
    public ResponseEntity<Starship> getStarshipById(Integer id) {
        if (id == null) {
            throw new FormatException(ID_PARAM_IS_NULL.getCode(), ID_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(starshipService.getStarshipById(id));
    }

    @Override
    public ResponseEntity<List<Starship>> getStarshipsByName(String name) {
        if (name == null) {
            throw new FormatException(NAME_PARAM_IS_NULL.getCode(), NAME_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(starshipService.getStarshipsByName(name));
    }
}
