package org.alohaspark.tron.acronymwebapp;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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

    public static Acronym createSingleDefAcronym(String name, String definition, boolean verified) {

        List<Definition> definitionList = new ArrayList<Definition>();
        definitionList.add(new Definition(definition,verified));
        return new Acronym(name, definitionList);

    }

    public String getName() {
        return name;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }


    @Override
    public String toString() {
        return String.format("Acronym[id=%s, name='%s', definition='%s']",
                this.id, this.name, this.definitions.toString());
    }

}
