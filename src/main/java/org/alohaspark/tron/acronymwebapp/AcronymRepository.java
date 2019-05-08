package org.alohaspark.tron.acronymwebapp;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcronymRepository extends MongoRepository<Acronym, String> {

    public Acronym findByName(String name);
    public Acronym findByDefinition(String definition);

}


