package com.conexaproject.star_wars_app.integrationtest;

import com.conexaproject.star_wars_app.controller.PeopleControllerImpl;
import com.conexaproject.star_wars_app.dto.BasicDataResponse;
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
class PeopleIntegrationTest {

    @Autowired
    PeopleControllerImpl peopleController;

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
    void getAllCharacterSuccess() throws Exception {
        String urlExternalApi = "https://www.swapi.tech/api/people?page=1&limit=10";
        String urlController = "/starWarsApi/characters";
        BasicDataResponse charactersResponse = objectMapper.readValue(loadJsonFromFile("mock_all_people.json"), BasicDataResponse.class);

        when(restTemplate.exchange(urlExternalApi, HttpMethod.GET, httpEntity, BasicDataResponse.class)).thenReturn(ResponseEntity.status(200).body(charactersResponse));

         mockMvc.perform(get(urlController)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verify content type
                .andExpect(jsonPath("$[0].name").value("Luke Skywalker")) // Verify the first character's name
                .andExpect(jsonPath("$[1].name").value("C-3PO")); // Verify the second character's name

        verify(restTemplate, times(1)).exchange(urlExternalApi, HttpMethod.GET, httpEntity, BasicDataResponse.class);
    }
}
