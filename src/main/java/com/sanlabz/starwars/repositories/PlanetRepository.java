package com.sanlabz.starwars.repositories;

import com.sanlabz.starwars.models.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, Long> {

    @Query("{ 'name' : { '$regex' : ?0 , $options: 'i'}}")
    List<Planet> findByNameList(String name);
}
