package org.alohaspark.tron.acronymwebapp;

public class Acronym {

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
}
