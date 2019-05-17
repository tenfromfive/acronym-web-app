package org.alohaspark.tron.acronymwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

@RestController
public class AcronymController {

    @Autowired
    private AcronymService service;

    @Autowired
    private AcronymRepository repository;

    @CrossOrigin
    @RequestMapping("/acronyms")
    public AcronymContainer httpAcronymResponse(@RequestParam(name = "bullets", defaultValue = "") String bullets) {

        //return "{\"acronyms\":[{\"name\":\"AA\",\"definitions\":[{\"definition\":\"AA Definition\",\"verified\":true}]}]}";

        List<Acronym> acronyms = this.service.findAcronyms(bullets);
        return new AcronymContainer(acronyms);
    }

    @PostMapping("/acronyms")
    public ResponseEntity newAcronym(@RequestBody Map<String, String> newAcronym) {

        service.addAcronym(newAcronym);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}

