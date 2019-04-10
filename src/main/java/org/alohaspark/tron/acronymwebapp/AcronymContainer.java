package org.alohaspark.tron.acronymwebapp;

import java.util.List;

public class AcronymContainer {

    private final List<Acronym> acronyms;

    public AcronymContainer(List<Acronym> acronyms) {
        this.acronyms = acronyms;
    }

    public List<Acronym> getAcronyms() {
        return acronyms;
    }

}
