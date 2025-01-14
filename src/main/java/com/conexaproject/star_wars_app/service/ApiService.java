package com.conexaproject.star_wars_app.service;

import com.conexaproject.star_wars_app.exceptions.GeneralException;
import com.conexaproject.star_wars_app.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.conexaproject.star_wars_app.utility.Errors.CONNECTION_EXCEPTION;
import static com.conexaproject.star_wars_app.utility.Errors.GENERAL_EXCEPTION;

@Service
public class ApiService {

    @Value("${starwarsapi.baseUrl}")
    private String starWarsApiBaseUrl;

    @Autowired
    RestTemplate restTemplate;

    public <T> ResponseEntity<T> callApi(String endpoint, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "PostmanRuntime/7.43.0");
        headers.set("Content-Type", "application/json");

        HttpEntity<String> headersString = new HttpEntity<>(headers);
        String url = starWarsApiBaseUrl + endpoint;

        try {
            return restTemplate.exchange(url, HttpMethod.GET, headersString, responseType);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                throw new ServiceException(CONNECTION_EXCEPTION.getCode(), String.format(CONNECTION_EXCEPTION.getMessage(), e.getMessage()));
            }

        } catch (Exception e) {
            throw new GeneralException(GENERAL_EXCEPTION.getCode(), String.format(GENERAL_EXCEPTION.getMessage(), e.getMessage()));
        }
    }
}

