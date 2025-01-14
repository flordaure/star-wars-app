package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.people.PeopleByIdResponse;
import com.conexaproject.star_wars_app.dto.people.PeopleByNameResponse;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.characters.Character;
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
class PeopleServiceImplTest {

    @InjectMocks
    PeopleServiceImpl peopleService;

    @Mock
    ApiService apiService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(peopleService, "peopleEndpoint", "/people");
    }

    @Test
    public void getAllPeople() throws Exception {

        String peopleJson = loadJsonFromFile("mock_all_people.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BasicDataResponse allPeopleMock = objectMapper.readValue(peopleJson, BasicDataResponse.class);

        Mockito.when(apiService.callApi("/people?page=1&limit=10", BasicDataResponse.class)).thenReturn(new ResponseEntity<>(allPeopleMock, HttpStatus.OK));

        List<BasicInformation> characters = peopleService.getAllPeople();
        Mockito.verify(apiService, Mockito.times(1)).callApi("/people?page=1&limit=10", BasicDataResponse.class);
        Assertions.assertNotNull(characters);
        Assertions.assertFalse(characters.isEmpty());
        Assertions.assertEquals(10, characters.size());

    }

    @Test
    public void getPeopleFromSpecificPage() throws Exception {

        String peopleJson = loadJsonFromFile("mock_all_people.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BasicDataResponse allPeopleMock = objectMapper.readValue(peopleJson, BasicDataResponse.class);

        Mockito.when(apiService.callApi("/people?page=1&limit=10", BasicDataResponse.class)).thenReturn(new ResponseEntity<>(allPeopleMock, HttpStatus.OK));

        List<BasicInformation> characters = peopleService.getPeopleOfSpecificPages(Collections.singletonList(1));
        Mockito.verify(apiService, Mockito.times(1)).callApi("/people?page=1&limit=10", BasicDataResponse.class);
        Assertions.assertNotNull(characters);
        Assertions.assertFalse(characters.isEmpty());
        Assertions.assertEquals(10, characters.size());

    }

    @Test
    public void getPeopleById() throws Exception {

        String peopleJson = loadJsonFromFile("mock_one_character.json");
        ObjectMapper objectMapper = new ObjectMapper();

        PeopleByIdResponse characterMock = objectMapper.readValue(peopleJson, PeopleByIdResponse.class);

        Mockito.when(apiService.callApi("/people/2", PeopleByIdResponse.class)).thenReturn(new ResponseEntity<>(characterMock, HttpStatus.OK));

        Character character = peopleService.getPeopleById(2);
        Mockito.verify(apiService, Mockito.times(1)).callApi("/people/2", PeopleByIdResponse.class);
        Assertions.assertNotNull(character);
        Assertions.assertEquals("2", character.getUid());
        Assertions.assertEquals("C-3PO", character.getName());

    }

    @Test
    public void getPeopleByName() throws Exception {

        String peopleJson = loadJsonFromFile("mock_character_by_name.json");
        ObjectMapper objectMapper = new ObjectMapper();

        PeopleByNameResponse characterMock = objectMapper.readValue(peopleJson, PeopleByNameResponse.class);

        Mockito.when(apiService.callApi("/people?name=C-3PO", PeopleByNameResponse.class)).thenReturn(new ResponseEntity<>(characterMock, HttpStatus.OK));

        List<Character> characters = peopleService.getPeopleByName("C-3PO");
        Mockito.verify(apiService, Mockito.times(1)).callApi("/people?name=C-3PO", PeopleByNameResponse.class);
        Assertions.assertFalse(characters.isEmpty());
        Assertions.assertEquals(1, characters.size());
        Assertions.assertEquals("2", characters.get(0).getUid());
        Assertions.assertEquals("C-3PO", characters.get(0).getName());

    }

}