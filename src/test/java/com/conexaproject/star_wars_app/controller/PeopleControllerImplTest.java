package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.people.PeopleByIdResponse;
import com.conexaproject.star_wars_app.dto.people.PeopleByNameResponse;
import com.conexaproject.star_wars_app.mappers.BasicDataMapper;
import com.conexaproject.star_wars_app.mappers.CharacterMapper;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.characters.Character;
import com.conexaproject.star_wars_app.service.PeopleServiceImpl;
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
class PeopleControllerImplTest {

    @InjectMocks
    private PeopleControllerImpl peopleController;

    @Mock
    private PeopleServiceImpl peopleService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllCharactersSuccess() throws IOException {

        BasicDataResponse charactersResponse = objectMapper.readValue(loadJsonFromFile("mock_all_people.json"), BasicDataResponse.class);
        List<BasicInformation> characters = charactersResponse.getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList());
        when(peopleService.getAllPeople()).thenReturn(characters);

        ResponseEntity<List<BasicInformation>> characterResponseEntity = peopleController.getAllCharacters();
        Assertions.assertNotNull(characterResponseEntity);
        Assertions.assertNotNull(characterResponseEntity.getBody());
        Assertions.assertFalse(characterResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(10, characterResponseEntity.getBody().size());
        Assertions.assertEquals(characters.get(0).getName(), characterResponseEntity.getBody().get(0).getName());
    }

    @Test
    public void getCharactersFromSpecificPagesSuccess() throws IOException {

        BasicDataResponse charactersResponse = objectMapper.readValue(loadJsonFromFile("mock_all_people.json"), BasicDataResponse.class);
        List<BasicInformation> characters = charactersResponse.getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList());
        when(peopleService.getPeopleOfSpecificPages(Collections.singletonList(1))).thenReturn(characters);

        ResponseEntity<List<BasicInformation>> characterResponseEntity = peopleController.getCharactersFromPages(Collections.singletonList(1));
        Assertions.assertNotNull(characterResponseEntity);
        Assertions.assertNotNull(characterResponseEntity.getBody());
        Assertions.assertFalse(characterResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(10, characterResponseEntity.getBody().size());
        Assertions.assertEquals(characters.get(0).getName(), characterResponseEntity.getBody().get(0).getName());
    }

    @Test
    public void getCharacterByIdSuccess() throws IOException {
        PeopleByIdResponse peopleById = objectMapper.readValue(loadJsonFromFile("mock_one_character.json"), PeopleByIdResponse.class);
        Character character = CharacterMapper.getCharacterData(peopleById.getResult());
        when(peopleService.getPeopleById(2)).thenReturn(character);
        ResponseEntity<Character> characterResponseEntity = peopleController.getCharacterById(2);
        Assertions.assertNotNull(characterResponseEntity);
        Assertions.assertNotNull(characterResponseEntity.getBody());
        Assertions.assertEquals(character.getName(), characterResponseEntity.getBody().getName());
    }

    @Test
    public void getCharacterByNameSuccess() throws IOException {
        PeopleByNameResponse peopleByNameResponse = objectMapper.readValue(loadJsonFromFile("mock_character_by_name.json"), PeopleByNameResponse.class);
        List<Character> characters = peopleByNameResponse.getResult().stream().map(CharacterMapper::getCharacterData).collect(Collectors.toList());
        when(peopleService.getPeopleByName("C-3PO")).thenReturn(characters);
        ResponseEntity<List<Character>> characterResponseEntity = peopleController.getCharactersByName("C-3PO");
        Assertions.assertNotNull(characterResponseEntity);
        Assertions.assertNotNull(characterResponseEntity.getBody());
        Assertions.assertFalse(characterResponseEntity.getBody().isEmpty());
        Assertions.assertEquals(characters.get(0).getName(), characterResponseEntity.getBody().get(0).getName());
    }

}

