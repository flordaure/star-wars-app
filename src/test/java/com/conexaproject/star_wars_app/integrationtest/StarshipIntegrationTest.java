package com.conexaproject.star_wars_app.integrationtest;

import com.conexaproject.star_wars_app.controller.StarshipControllerImpl;
import com.conexaproject.star_wars_app.dto.starships.StarshipsByIdResponse;
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
class StarshipIntegrationTest {

    @Autowired
    StarshipControllerImpl starshipController;

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
    void getStarshipByIdSuccess() throws Exception {
        String urlExternalApi = "https://www.swapi.tech/api/starships/3";
        String urlController = "/starWarsApi/starshipById";
        StarshipsByIdResponse starshipResponse = objectMapper.readValue(loadJsonFromFile("mock_one_starship.json"), StarshipsByIdResponse.class);

        when(restTemplate.exchange(urlExternalApi, HttpMethod.GET, httpEntity, StarshipsByIdResponse.class)).thenReturn(ResponseEntity.status(200).body(starshipResponse));

        mockMvc.perform(get(urlController)
                .param("id", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())  // HTTP 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verify content type
                .andExpect(jsonPath("$.uid").value("3"))
                .andExpect(jsonPath("$.name").value("Star Destroyer"));

        verify(restTemplate, times(1)).exchange(urlExternalApi, HttpMethod.GET, httpEntity, StarshipsByIdResponse.class);
    }

    @Test
    public void testGetStarshipByIdBadRequest() throws Exception {
        String urlController = "/starWarsApi/starshipById";
        mockMvc.perform(get(urlController)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
