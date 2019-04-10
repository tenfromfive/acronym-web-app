package org.alohaspark.tron.acronymwebapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AcronymController {



    @RequestMapping("/acronyms")
    public AcronymContainer httpAcronymResponse(@RequestParam(name = "bullets", defaultValue = "") String bullets) {

        List<Acronym> acronyms = this.findAcronyms(bullets);
        return new AcronymContainer(acronyms);

    }

    private List<Acronym> findAcronyms(String bullets) {

        List<Acronym> acronyms = new ArrayList<Acronym>();

        acronyms.add(new Acronym("AA", "Administrative Assistant"));
        acronyms.add(new Acronym("CGO", "Company Grade Officer"));
        acronyms.add(new Acronym("NCO", "Non-Commissioned Officer"));

        return acronyms;
    }

}
