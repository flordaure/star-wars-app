package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.exceptions.FormatException;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.characters.Character;
import com.conexaproject.star_wars_app.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.conexaproject.star_wars_app.utility.Errors.*;

@RestController
public class PeopleControllerImpl extends ApiController implements PeopleController {

    @Autowired
    PeopleService peopleService;

    @Override
    public ResponseEntity<List<BasicInformation>> getAllCharacters() {
        return ResponseEntity.ok(peopleService.getAllPeople());
    }

    @Override
    public ResponseEntity<List<BasicInformation>> getCharactersFromPages(List<Integer> pages) {
        if (pages.isEmpty()) {
            throw new FormatException(PAGES_PARAM_IS_NULL.getCode(), PAGES_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(peopleService.getPeopleOfSpecificPages(pages));
    }

    @Override
    public ResponseEntity<Character> getCharacterById(Integer id) {
        if (id == null) {
            throw new FormatException(ID_PARAM_IS_NULL.getCode(), ID_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(peopleService.getPeopleById(id));
    }

    @Override
    public ResponseEntity<List<Character>> getCharactersByName(String name) {
        if (name == null) {
            throw new FormatException(NAME_PARAM_IS_NULL.getCode(), NAME_PARAM_IS_NULL.getMessage());
        }
        return ResponseEntity.ok(peopleService.getPeopleByName(name));
    }
}
