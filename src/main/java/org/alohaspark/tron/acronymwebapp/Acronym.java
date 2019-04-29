package org.alohaspark.tron.acronymwebapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="acronyms")
public class Acronym {

    @Id
    private String id;

    private final String name;
    private final String definition;

    public Acronym(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    @Override
    public String toString() {
        return String.format("Acronym[id=%s, name='%s', definition='%s']",
                this.id, this.name, this.definition);
    }

}
