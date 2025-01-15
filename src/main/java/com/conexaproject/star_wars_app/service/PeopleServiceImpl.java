package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.people.*;
import com.conexaproject.star_wars_app.exceptions.DataException;
import com.conexaproject.star_wars_app.exceptions.ServiceException;
import com.conexaproject.star_wars_app.mappers.BasicDataMapper;
import com.conexaproject.star_wars_app.mappers.CharacterMapper;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.characters.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.conexaproject.star_wars_app.mappers.CharacterMapper.getCharacterData;
import static com.conexaproject.star_wars_app.utility.Errors.*;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Value("${people.endpoint}")
    private String peopleEndpoint;

    @Autowired
    private ApiService apiService;

    @Cacheable("people")
    @Override
    public List<BasicInformation> getAllPeople() {
        List<BasicInformation> basicData = new ArrayList<>();
        String nextPageUrl = peopleEndpoint + "?page=1&limit=10";

        while (nextPageUrl != null) {
            ResponseEntity<BasicDataResponse> peopleResponse = apiService.callApi(nextPageUrl, BasicDataResponse.class);
            if (peopleResponse.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), peopleResponse.getStatusCode()));
            }
            if (peopleResponse.getBody() != null && !peopleResponse.getBody().getResults().isEmpty()) {
                basicData.addAll(peopleResponse.getBody().getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList()));

            }
            //se obtiene la última parte de la url
            nextPageUrl = peopleResponse.getBody() != null && peopleResponse.getBody().getNext() != null ? peopleResponse.getBody().getNext().split("/api")[1] : null;

        }
        return basicData;
    }


    @Override
    public List<BasicInformation> getPeopleOfSpecificPages(List<Integer> pagesNumbers) {
        List<BasicInformation> basicData = new ArrayList<>();
        for (Integer page : pagesNumbers) {

            String nextPageUrl = peopleEndpoint + "?page=" + page + "&limit=10";
            ResponseEntity<BasicDataResponse> peopleResponse = apiService.callApi(nextPageUrl, BasicDataResponse.class);
            if (peopleResponse.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), peopleResponse.getStatusCode()));
            }
            if (peopleResponse.getBody() != null && !peopleResponse.getBody().getResults().isEmpty()) {
                basicData.addAll(peopleResponse.getBody().getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList()));

            }
        }
        return basicData;
    }

    @Override
    public Character getPeopleById(Integer id) {
        String url = peopleEndpoint + "/" + id;
        Character character = null;

        ResponseEntity<PeopleByIdResponse> peopleById = apiService.callApi(url, PeopleByIdResponse.class);
        if (peopleById.getStatusCode() != HttpStatus.OK && peopleById.getStatusCode() != HttpStatus.NOT_FOUND) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), peopleById.getStatusCode()));
        }

        if (peopleById.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new DataException(ID_NOT_FOUND.getCode(), String.format(ID_NOT_FOUND.getMessage(), id));
        }
        if (peopleById.getBody() != null && peopleById.getBody().getResult() != null) {
            character = getCharacterData(peopleById.getBody().getResult());
        }
        return character;
    }

    @Override
    public List<Character> getPeopleByName(String name) {

        List<Character> completeData = new ArrayList<>();
        String url = peopleEndpoint + "?name=" + name;
        ResponseEntity<PeopleByNameResponse> peopleByName = apiService.callApi(url, PeopleByNameResponse.class);

        if (peopleByName.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), peopleByName.getStatusCode()));
        }

        if (peopleByName.getBody() != null && peopleByName.getBody().getResult().isEmpty()) {
            throw new DataException(NAME_NOT_FOUND.getCode(), String.format(NAME_NOT_FOUND.getMessage(), name));
        }
        if (peopleByName.getBody() != null && !peopleByName.getBody().getResult().isEmpty()) {
            completeData.addAll(peopleByName.getBody().getResult().stream().map(CharacterMapper::getCharacterData).collect(Collectors.toList()));
        }
        return completeData;

    }

}


