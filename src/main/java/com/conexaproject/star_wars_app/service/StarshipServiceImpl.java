package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.starships.StarshipsByIdResponse;
import com.conexaproject.star_wars_app.dto.starships.StarshipsByNameResponse;
import com.conexaproject.star_wars_app.exceptions.DataException;
import com.conexaproject.star_wars_app.exceptions.ServiceException;
import com.conexaproject.star_wars_app.mappers.BasicDataMapper;
import com.conexaproject.star_wars_app.mappers.StarshipMapper;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.starships.Starship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.conexaproject.star_wars_app.mappers.StarshipMapper.getStarshipData;
import static com.conexaproject.star_wars_app.utility.Errors.*;

@Service
public class StarshipServiceImpl implements StarshipService {

    @Value("${starships.endpoint}")
    private String starshipsEndpoint;

    @Autowired
    private ApiService apiService;

    @Cacheable("starships")
    @Override
    public List<BasicInformation> getAllStarships() {
        List<BasicInformation> basicData = new ArrayList<>();
        String nextPageUrl = starshipsEndpoint + "?page=1&limit=10";

        while (nextPageUrl != null) {
            ResponseEntity<BasicDataResponse> starshipsResponse = apiService.callApi(nextPageUrl, BasicDataResponse.class);
            if (starshipsResponse.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), starshipsResponse.getStatusCode()));
            }
            if (starshipsResponse.getBody() != null && !starshipsResponse.getBody().getResults().isEmpty()) {
                basicData.addAll(starshipsResponse.getBody().getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList()));

            }
            //se obtiene la última parte de la url
            nextPageUrl = starshipsResponse.getBody() != null && starshipsResponse.getBody().getNext() != null ? starshipsResponse.getBody().getNext().split("/api")[1] : null;

        }
        return basicData;
    }


    @Override
    public List<BasicInformation> getStarshipsOfSpecificPages(List<Integer> pagesNumbers) {
        List<BasicInformation> basicData = new ArrayList<>();
        for (Integer page : pagesNumbers) {

            String nextPageUrl = starshipsEndpoint + "?page=" + page + "&limit=10";
            ResponseEntity<BasicDataResponse> starshipsResponse = apiService.callApi(nextPageUrl, BasicDataResponse.class);
            if (starshipsResponse.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), starshipsResponse.getStatusCode()));
            }
            if (starshipsResponse.getBody() != null && !starshipsResponse.getBody().getResults().isEmpty()) {
                basicData.addAll(starshipsResponse.getBody().getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList()));

            }
        }
        return basicData;
    }

    @Override
    public Starship getStarshipById(Integer id) {
        String url = starshipsEndpoint + "/" + id;
        Starship starship = null;

        ResponseEntity<StarshipsByIdResponse> starshipById = apiService.callApi(url, StarshipsByIdResponse.class);
        if (starshipById.getStatusCode() != HttpStatus.OK && starshipById.getStatusCode() != HttpStatus.NOT_FOUND) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), starshipById.getStatusCode()));
        }

        if (starshipById.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new DataException(ID_NOT_FOUND.getCode(), String.format(ID_NOT_FOUND.getMessage(), id));
        }
        if (starshipById.getBody() != null && starshipById.getBody().getResult() != null) {
            starship = getStarshipData(starshipById.getBody().getResult());
        }
        return starship;
    }

    @Override
    public List<Starship> getStarshipsByName(String name) {

        List<Starship> completeData = new ArrayList<>();
        String url = starshipsEndpoint + "?name=" + name;
        ResponseEntity<StarshipsByNameResponse> starshipsByName = apiService.callApi(url, StarshipsByNameResponse.class);

        if (starshipsByName.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), starshipsByName.getStatusCode()));
        }

        if (starshipsByName.getBody() != null && starshipsByName.getBody().getResult().isEmpty()) {
            throw new DataException(NAME_NOT_FOUND.getCode(), String.format(NAME_NOT_FOUND.getMessage(), name));
        }
        if (starshipsByName.getBody() != null && !starshipsByName.getBody().getResult().isEmpty()) {
            completeData.addAll(starshipsByName.getBody().getResult().stream().map(StarshipMapper::getStarshipData).collect(Collectors.toList()));
        }
        return completeData;

    }

}


