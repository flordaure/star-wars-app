package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.starships.Starship;

import java.util.List;

public interface StarshipService {

    List<BasicInformation> getAllStarships();
    List<BasicInformation> getStarshipsOfSpecificPages(List<Integer> pagesNumbers);
    Starship getStarshipById(Integer id);
    List<Starship> getStarshipsByName(String name);
}
