package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.characters.Character;

import java.util.List;

public interface PeopleService {

    List<BasicInformation> getAllPeople();
    List<BasicInformation> getPeopleOfSpecificPages(List<Integer> pagesNumbers);
    Character getPeopleById(Integer id);
    List<Character> getPeopleByName(String name);
}
