package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.starships.StarshipsByIdResponse;
import com.conexaproject.star_wars_app.dto.starships.StarshipsByNameResponse;
import com.conexaproject.star_wars_app.mappers.BasicDataMapper;
import com.conexaproject.star_wars_app.mappers.StarshipMapper;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.starships.Starship;
import com.conexaproject.star_wars_app.service.StarshipServiceImpl;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.conexaproject.star_wars_app.Util.loadJsonFromFile;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarshipControllerImplTest {

    @InjectMocks
    StarshipControllerImpl starshipController;

    @Mock
    StarshipServiceImpl starshipService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllStarshipsSuccess() throws IOException {

        BasicDataResponse starshipResponse = objectMapper.readValue(loadJsonFromFile("mock_all_starship.json"), BasicDataResponse.class);
        List<BasicInformation> starships = starshipResponse.getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList());
        when(starshipService.getAllStarships()).thenReturn(starships);

        ResponseEntity<List<BasicInformation>> starshipResponseEntity = starshipController.getAllStarships();
        Assertions.assertNotNull(starshipResponseEntity);
        Assertions.assertNotNull(starshipResponseEntity.getBody());
        Assertions.assertFalse(starshipResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(10, starshipResponseEntity.getBody().size());
        Assertions.assertEquals(starships.get(0).getName(), starshipResponseEntity.getBody().get(0).getName());
    }

    @Test
    public void getStarshipsFromSpecificPagesSuccess() throws IOException {

        BasicDataResponse charactersResponse = objectMapper.readValue(loadJsonFromFile("mock_all_starship.json"), BasicDataResponse.class);
        List<BasicInformation> starships = charactersResponse.getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList());
        when(starshipService.getStarshipsOfSpecificPages(Collections.singletonList(1))).thenReturn(starships);

        ResponseEntity<List<BasicInformation>> starshipsResponseEntity = starshipController.getStarshipsFromPages(Collections.singletonList(1));
        Assertions.assertNotNull(starshipsResponseEntity);
        Assertions.assertNotNull(starshipsResponseEntity.getBody());
        Assertions.assertFalse(starshipsResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(10, starshipsResponseEntity.getBody().size());
        Assertions.assertEquals(starships.get(0).getName(), starshipsResponseEntity.getBody().get(0).getName());
    }

    @Test
    public void getStarshipsByIdSuccess() throws IOException {
        StarshipsByIdResponse starshipsById = objectMapper.readValue(loadJsonFromFile("mock_one_starship.json"), StarshipsByIdResponse.class);
        Starship starship = StarshipMapper.getStarshipData(starshipsById.getResult());
        when(starshipService.getStarshipById(3)).thenReturn(starship);
        ResponseEntity<Starship> starshipsResponseEntity = starshipController.getStarshipById(3);
        Assertions.assertNotNull(starshipsResponseEntity);
        Assertions.assertNotNull(starshipsResponseEntity.getBody());
        Assertions.assertEquals(starship.getName(), starshipsResponseEntity.getBody().getName());
    }

    @Test
    public void getStarshipByNameSuccess() throws IOException {
        StarshipsByNameResponse starshipByNameResponse = objectMapper.readValue(loadJsonFromFile("mock_starship_by_name.json"),  StarshipsByNameResponse.class);
        List<Starship> starships = starshipByNameResponse.getResult().stream().map(StarshipMapper::getStarshipData).collect(Collectors.toList());
        when(starshipService.getStarshipsByName("Star Destroyer")).thenReturn(starships);
        ResponseEntity<List<Starship>> starshipsResponseEntity = starshipController.getStarshipsByName("Star Destroyer");
        Assertions.assertNotNull(starshipsResponseEntity);
        Assertions.assertNotNull(starshipsResponseEntity.getBody());
        Assertions.assertFalse(starshipsResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(starships.get(0).getName(), starshipsResponseEntity.getBody().get(0).getName());
    }

}