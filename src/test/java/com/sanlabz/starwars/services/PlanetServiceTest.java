package com.sanlabz.starwars.services;

import com.sanlabz.starwars.feign.PlanetClient;
import com.sanlabz.starwars.models.Planet;
import com.sanlabz.starwars.repositories.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
public class PlanetServiceTest {

    private PlanetService planetService;

    @MockBean
    private PlanetRepository planetRepository;

    @MockBean
    private PlanetClient planetClient;

    @Before
    public void setUp() {
        planetService = new PlanetService(planetRepository, planetClient);
    }

    @Test
    public void haveSearchPlanets() {
        List<Planet> list = List.of(Planet.builder().name("A").build());
        when(planetRepository.findAll()).thenReturn(list);
        List<Planet> planets = planetService.findAll();
        assertThat(planets.size()).isPositive();
    }

    @Test
    public void haveSearchPlanetByName() {
        when(planetRepository.findByNameList("test")).thenReturn(Collections.emptyList());
        List<Planet> planets = planetService.findByName("test");
        assertThat(planets.isEmpty()).isTrue();
    }

    @Test
    public void haveSearchPlanetById() {
        when(planetRepository.findById(Long.valueOf(123))).thenReturn(Optional.of(new Planet()));
        Optional<Planet> planets = planetService.findById(Long.valueOf(123));
        assertThat(planets.isPresent()).isTrue();
    }

    @Test
    public void haveDeletePlanetById() {
        verify(planetRepository, times(0)).deleteById(Long.valueOf(123));
        planetService.delete(Long.valueOf(123));
    }
}
