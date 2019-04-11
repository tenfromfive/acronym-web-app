package org.alohaspark.tron.acronymwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AcronymController {

    @Autowired
    private AcronymRepository repository;


    @CrossOrigin
    @RequestMapping("/acronyms")
    public AcronymContainer httpAcronymResponse(@RequestParam(name = "bullets", defaultValue = "") String bullets) {

        this.saveAcronyms();
        List<Acronym> acronyms = this.findAcronyms(bullets);
        return new AcronymContainer(acronyms);

    }

    private void saveAcronyms() {
        repository.deleteAll();
        repository.save(new Acronym("AA", "Administrative Assistant"));
        repository.save(new Acronym("CGO", "Company Grade Officer"));
        repository.save(new Acronym("NCO", "Non-Commissioned Officer"));
    }

    private List<Acronym> findAcronyms(String bullets) {

        List<Acronym> acronyms = new ArrayList<Acronym>();

        String[] acs = {"AAs","CGO","NCO"};
        for (String ac : acs) {
            Acronym tempAc = repository.findByName(ac);
            if (tempAc==null) {
                acronyms.add(new Acronym(ac,""));
            } else {
                acronyms.add(tempAc);
            }
        }

        return acronyms;
    }
}
