package com.movieDB.movieDB;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@EnableWebMvc
@SpringBootTest
public class MovieControllerTest {

    private static final String VALIDATION_ENDPOINT = "http://localhost:8080/movies/all";
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private static final String ACTUATOR_BASE_PATH = "/admin";

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @WithMockUser(username = "john.doe", password = "secret")
    @Test
    void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = testRestTemplate
                .withBasicAuth("$.username", "$.password")
                .getForEntity("http://localhost:8181", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void whenGetActuatorInfoAndHealth_messageReturned() throws Exception {
        ResultActions resultInfo = mockMvc.perform(get(ACTUATOR_BASE_PATH + "/info"));
        ResultActions resultHealth = mockMvc.perform(get(ACTUATOR_BASE_PATH + "/health"));

        resultInfo.andExpect(status().isOk());
        resultHealth.andExpect(status().isOk());
    }

    @Test
    void getAllMovies_success() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(
                        MockMvcRequestBuilders.get(VALIDATION_ENDPOINT)
                        .contentType(CONTENT_TYPE)
                        .characterEncoding(ENCODING))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        TypeReference<Map<String,Object>> typeRef = new TypeReference<>() {};
        Map<String,Object> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),typeRef);
        assertTrue(response.containsKey("movies"));
    }
}
