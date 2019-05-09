package org.alohaspark.tron.acronymwebapp;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "acronyms")
public class Acronym {

    @Id
    private String id;

    private final String name;
    private final List<Definition> definitions;

    public Acronym(String name, List<Definition> definitions) {
        this.name = name;
        this.definitions = definitions;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean getVerified(){ return verified;}

    @Override
    public String toString() {
        return String.format("Acronym[id=%s, name='%s', definition='%s']",
                this.id, this.name, this.definition);
    }

}
