package com.sanlabz.starwars.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sanlabz.starwars.models.Planet;
import com.sanlabz.starwars.services.PlanetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class PlanetResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlanetService planetService;

    @Test
    public void haveSearchPlanets() throws Exception {
        mvc.perform(get("/planets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(planetService, times(1)).findAll();
    }

    @Test
    public void haveSearchPlanetByName() throws Exception {
        mvc.perform(get("/planets/Tatooine/name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(planetService, times(1)).findByName(any());
    }

    @Test
    public void haveSearchPlanetById() throws Exception {
        mvc.perform(get("/planets/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(planetService, times(1)).findById(any());
    }

    @Test
    public void haveSavePlanet() throws Exception {
        Planet planet = Planet.builder().name("Tatooine").build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(planet);

        mvc.perform(post("/planets")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(planetService, times(1)).save(any());
    }

    @Test
    public void haveDeletePlanet() throws Exception {
        mvc.perform(delete("/planets/123"))
                .andExpect(status().isOk());

        verify(planetService, times(1)).delete(any());
    }
}
