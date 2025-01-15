package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.vehicles.VehiclesByIdResponse;
import com.conexaproject.star_wars_app.dto.vehicles.VehiclesByNameResponse;
import com.conexaproject.star_wars_app.exceptions.DataException;
import com.conexaproject.star_wars_app.exceptions.ServiceException;
import com.conexaproject.star_wars_app.mappers.BasicDataMapper;
import com.conexaproject.star_wars_app.mappers.VehicleMapper;
import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.vehicles.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.conexaproject.star_wars_app.mappers.VehicleMapper.getVehicleData;
import static com.conexaproject.star_wars_app.utility.Errors.*;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Value("${vehicles.endpoint}")
    private String vehicleEndpoint;

    @Autowired
    private ApiService apiService;

    @Cacheable("vehicles")
    @Override
    public List<BasicInformation> getAllVehicles() {
        List<BasicInformation> basicData = new ArrayList<>();
        String nextPageUrl = vehicleEndpoint + "?page=1&limit=10";

        while (nextPageUrl != null) {
            ResponseEntity<BasicDataResponse> vehicleResponse = apiService.callApi(nextPageUrl, BasicDataResponse.class);
            if (vehicleResponse.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), vehicleResponse.getStatusCode()));
            }
            if (vehicleResponse.getBody() != null && !vehicleResponse.getBody().getResults().isEmpty()) {
                basicData.addAll(vehicleResponse.getBody().getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList()));

            }
            //se obtiene la última parte de la url
            nextPageUrl = vehicleResponse.getBody() != null && vehicleResponse.getBody().getNext() != null ? vehicleResponse.getBody().getNext().split("/api")[1] : null;

        }
        return basicData;
    }


    @Override
    public List<BasicInformation> getVehiclesOfSpecificPages(List<Integer> pagesNumbers) {
        List<BasicInformation> basicData = new ArrayList<>();
        for (Integer page : pagesNumbers) {

            String nextPageUrl = vehicleEndpoint + "?page=" + page + "&limit=10";
            ResponseEntity<BasicDataResponse> vehicleResponse = apiService.callApi(nextPageUrl, BasicDataResponse.class);
            if (vehicleResponse.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), vehicleResponse.getStatusCode()));
            }
            if (vehicleResponse.getBody() != null && !vehicleResponse.getBody().getResults().isEmpty()) {

                basicData.addAll(vehicleResponse.getBody().getResults().stream().map(BasicDataMapper::getBasicData).collect(Collectors.toList()));
            }
        }

        return basicData;
}

    @Override
    public Vehicle getVehicleById(Integer id) {
        String url = vehicleEndpoint + "/" + id;

        Vehicle vehicle = null;
        ResponseEntity<VehiclesByIdResponse> vehicleById = apiService.callApi(url, VehiclesByIdResponse.class);
        if (vehicleById.getStatusCode() != HttpStatus.OK && vehicleById.getStatusCode() != HttpStatus.NOT_FOUND) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), vehicleById.getStatusCode()));
        }

        if (vehicleById.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new DataException(ID_NOT_FOUND.getCode(), String.format(ID_NOT_FOUND.getMessage(), id));
        }

        if (vehicleById.getBody() != null && vehicleById.getBody().getResult() != null) {
            vehicle = getVehicleData(vehicleById.getBody().getResult());
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getVehiclesByName(String name) {

        List<Vehicle> completeData = new ArrayList<>();
        String url = vehicleEndpoint + "?name=" + name;
        ResponseEntity<VehiclesByNameResponse> vehiclesByName = apiService.callApi(url, VehiclesByNameResponse.class);

        if (vehiclesByName.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HTTP_STATUS_CODE_NOT_OK.getCode(), String.format(HTTP_STATUS_CODE_NOT_OK.getMessage(), vehiclesByName.getStatusCode()));
        }
        if (vehiclesByName.getBody() != null && vehiclesByName.getBody().getResult().isEmpty()) {
            throw new DataException(NAME_NOT_FOUND.getCode(), String.format(NAME_NOT_FOUND.getMessage(), name));
        }
        if (vehiclesByName.getBody() != null && !vehiclesByName.getBody().getResult().isEmpty()) {

            completeData.addAll(vehiclesByName.getBody().getResult().stream().map(VehicleMapper::getVehicleData).collect(Collectors.toList()));
        }
        return completeData;

    }
}


