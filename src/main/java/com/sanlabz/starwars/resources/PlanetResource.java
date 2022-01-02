package com.sanlabz.starwars.resources;

import com.sanlabz.starwars.models.Planet;
import com.sanlabz.starwars.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetResource {

    @Autowired
    private PlanetService planetService;

    @GetMapping
    public List<Planet> findAll() {
        return this.planetService.findAll();
    }

    @GetMapping("/{name}/name")
    public ResponseEntity<List<Planet>> findByName(@PathVariable("name") String name) {
        List<Planet> planet = this.planetService.findByName(name);
        return ResponseEntity.ok(planet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Planet>> findById(@PathVariable("id") String id) {
        Optional<Planet> planet = this.planetService.findById(Long.parseLong(id));
        return ResponseEntity.ok(planet);
    }

    @PostMapping
    @ResponseBody
    public Planet save(@RequestBody Planet planet) {
        return this.planetService.save(planet);
    }

    @DeleteMapping(path ={"/{id}"})
    public void delete(@PathVariable Long id) {
        this.planetService.delete(id);
    }
}