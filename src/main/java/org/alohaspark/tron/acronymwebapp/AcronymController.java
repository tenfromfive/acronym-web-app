package org.alohaspark.tron.acronymwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.*;

@RestController
public class AcronymController {

    @Autowired
    private AcronymService service;


    @CrossOrigin
    @RequestMapping("/acronyms")
    public AcronymContainer httpAcronymResponse(@RequestParam(name = "bullets", defaultValue = "") String bullets) {

        //return "{\"acronyms\":[{\"name\":\"AA\",\"definitions\":[{\"definition\":\"AA Definition\",\"verified\":true}]}]}";

        List<Acronym> acronyms = this.service.findAcronyms(bullets);
        return new AcronymContainer(acronyms);
    }

    
}

