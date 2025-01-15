package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.starships.StarshipsByIdResponse;
import com.conexaproject.star_wars_app.dto.starships.StarshipsByNameResponse;
import com.conexaproject.star_wars_app.dto.vehicles.VehiclesByIdResponse;
import com.conexaproject.star_wars_app.dto.vehicles.VehiclesByNameResponse;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.starships.Starship;
import com.conexaproject.star_wars_app.model.vehicles.Vehicle;
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

import java.util.Collections;
import java.util.List;

import static com.conexaproject.star_wars_app.Util.loadJsonFromFile;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StarshipServiceImplTest {

    @InjectMocks
    StarshipServiceImpl starshipService;

    @Mock
    ApiService apiService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(starshipService, "starshipsEndpoint", "/starships");
    }

    @Test
    public void getAllStarships() throws Exception {

        String starshipsJson = loadJsonFromFile("mock_all_starship.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BasicDataResponse allStarshipsMock = objectMapper.readValue(starshipsJson, BasicDataResponse.class);

        Mockito.when(apiService.callApi("/starships?page=1&limit=10", BasicDataResponse.class)).thenReturn(new ResponseEntity<>(allStarshipsMock, HttpStatus.OK));

        List<BasicInformation> starships = starshipService.getAllStarships();
        Mockito.verify(apiService, Mockito.times(1)).callApi("/starships?page=1&limit=10", BasicDataResponse.class);
        Assertions.assertNotNull(starships);
        Assertions.assertFalse(starships.isEmpty());
        Assertions.assertEquals(10, starships.size());

    }

    @Test
    public void getStarshipsFromSpecificPage() throws Exception {

        String starshipsJson = loadJsonFromFile("mock_all_starship.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BasicDataResponse allStarshipsMock = objectMapper.readValue(starshipsJson, BasicDataResponse.class);

        Mockito.when(apiService.callApi("/starships?page=1&limit=10", BasicDataResponse.class)).thenReturn(new ResponseEntity<>(allStarshipsMock, HttpStatus.OK));

        List<BasicInformation> starships = starshipService.getStarshipsOfSpecificPages(Collections.singletonList(1));
        Mockito.verify(apiService, Mockito.times(1)).callApi("/starships?page=1&limit=10", BasicDataResponse.class);
        Assertions.assertNotNull(starships);
        Assertions.assertFalse(starships.isEmpty());
        Assertions.assertEquals(10, starships.size());

    }

    @Test
    public void getStarshipById() throws Exception {

        String starshipsJson = loadJsonFromFile("mock_one_starship.json");
        ObjectMapper objectMapper = new ObjectMapper();

        StarshipsByIdResponse starshipMock = objectMapper.readValue(starshipsJson, StarshipsByIdResponse.class);

        Mockito.when(apiService.callApi("/starships/3", StarshipsByIdResponse.class)).thenReturn(new ResponseEntity<>(starshipMock, HttpStatus.OK));

        Starship starship = starshipService.getStarshipById(3);
        Mockito.verify(apiService, Mockito.times(1)).callApi("/starships/3", StarshipsByIdResponse.class);
        Assertions.assertNotNull(starship);
        Assertions.assertEquals("3", starship.getUid());
        Assertions.assertEquals("Star Destroyer", starship.getName());

    }

    @Test
    public void getStarshipByName() throws Exception {

        String starshipsJson = loadJsonFromFile("mock_starship_by_name.json");
        ObjectMapper objectMapper = new ObjectMapper();

        StarshipsByNameResponse starshipMock = objectMapper.readValue(starshipsJson, StarshipsByNameResponse.class);

        Mockito.when(apiService.callApi("/starships?name=Star Destroyer", StarshipsByNameResponse.class)).thenReturn(new ResponseEntity<>(starshipMock, HttpStatus.OK));

        List<Starship> starships = starshipService.getStarshipsByName("Star Destroyer");
        Mockito.verify(apiService, Mockito.times(1)).callApi("/starships?name=Star Destroyer", StarshipsByNameResponse.class);
        Assertions.assertFalse(starships.isEmpty());
        Assertions.assertEquals(1, starships.size());
        Assertions.assertEquals("3", starships.get(0).getUid());
        Assertions.assertEquals("Star Destroyer", starships.get(0).getName());

    }


}