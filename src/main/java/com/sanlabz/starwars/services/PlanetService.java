package com.sanlabz.starwars.services;

import com.sanlabz.starwars.feign.PlanetClient;
import com.sanlabz.starwars.models.Planet;
import com.sanlabz.starwars.models.PlanetResponse;
import com.sanlabz.starwars.repositories.PlanetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PlanetService {

    private PlanetRepository planetRepository;

    private PlanetClient planetClient;

    public List<Planet> findAll() {
        return this.planetRepository.findAll();
    }

    public List<Planet> findByName(String name) {
        return this.planetRepository.findByNameList(name);
    }

    public Optional<Planet> findById(Long id) {
        return this.planetRepository.findById(id);
    }

    public Planet save(Planet planet) {
        PlanetResponse response = planetClient.getPlanetByName(planet.getName());

        if (!response.getResults().isEmpty() && Objects.isNull(planet.getFilms())) {
            response.getResults().forEach((r) -> {
                if (r.getName().trim().equalsIgnoreCase(planet.getName().trim()) && Objects.nonNull(r.getFilms())) {
                    planet.setFilms(r.getFilms().size());
                }
            });
        }

        return this.planetRepository.save(planet);
    }

    public void delete(Long id) {
        this.planetRepository.deleteById(id);
    }
}