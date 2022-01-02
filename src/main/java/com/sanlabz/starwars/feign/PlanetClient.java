package com.sanlabz.starwars.feign;

import com.sanlabz.starwars.models.PlanetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://swapi.dev/api", name = "PLANET-CLIENT")
public interface PlanetClient {

    @RequestMapping(method = RequestMethod.GET, value = "/planets/?search={name}", produces = "application/json")
    PlanetResponse getPlanetByName(@PathVariable("name") String name);
}
