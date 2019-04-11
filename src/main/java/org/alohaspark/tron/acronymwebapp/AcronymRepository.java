package org.alohaspark.tron.acronymwebapp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AcronymRepository extends MongoRepository<Acronym, String> {

    public Acronym findByName(String name);
    public Acronym findByDefinition(String definition);

}


