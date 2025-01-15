package com.conexaproject.star_wars_app.integrationtest;

import com.conexaproject.star_wars_app.controller.FilmControllerImpl;
import com.conexaproject.star_wars_app.dto.BasicDataResponse;
import com.conexaproject.star_wars_app.dto.films.FilmsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static com.conexaproject.star_wars_app.Util.loadJsonFromFile;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class FilmsIntegrationTest {

    @Autowired
    FilmControllerImpl filmController;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RestTemplate restTemplate;

    private HttpEntity httpEntity;

    @BeforeEach
    void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "PostmanRuntime/7.43.0");
        headers.set("Content-Type", "application/json");
        httpEntity = new HttpEntity(headers);
    }

    @Test
    void getFilmsByTitleSuccess() throws Exception {
        String urlExternalApi = "https://www.swapi.tech/api/films?title=A New Hope";
        String urlController = "/starWarsApi/filmsByTitle";
        FilmsResponse filmsResponse = objectMapper.readValue(loadJsonFromFile("mock_film_by_name.json"), FilmsResponse.class);

        when(restTemplate.exchange(urlExternalApi, HttpMethod.GET, httpEntity, FilmsResponse.class)).thenReturn(ResponseEntity.status(200).body(filmsResponse));

        mockMvc.perform(get(urlController)
                .param("title", "A New Hope")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verify content type
                .andExpect(jsonPath("$[0].title").value("A New Hope"));

        verify(restTemplate, times(1)).exchange(urlExternalApi, HttpMethod.GET, httpEntity, FilmsResponse.class);
    }
}
