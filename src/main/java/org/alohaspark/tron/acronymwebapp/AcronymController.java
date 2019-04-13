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
    private AcronymRepository repository;


    @CrossOrigin
    @RequestMapping("/acronyms")
    public AcronymContainer httpAcronymResponse(@RequestParam(name = "bullets", defaultValue = "") String bullets) {

        List<Acronym> acronyms = this.findAcronyms(bullets);
        return new AcronymContainer(acronyms);

    }

    private List<Acronym> findAcronyms(String bullets) {

        List<Acronym> acronyms = new ArrayList<Acronym>();
        // spaces at fron and end to counter endline char regex mismatching
        bullets = " " + bullets + " ";

        System.out.println("Bulets: " + bullets);

        //regex history//
        /*
        80% Solution: [A-Z]{2,}

        98% Solution: ([A-Z]+)?(\d|\w&\w|\w-\w|\w/\w)([A-Z]*)?\d*(?=\s)|([A-Z]{2,})
        */


       String regex1 = "([A-Z]+)?(\\d|\\w&\\w|\\w-\\w|\\w/\\w)([A-Z]*)?\\d*(?=\\s)|([A-Z]{2,})";
       Pattern pattern = Pattern.compile(regex1); 
        
       Matcher m = pattern.matcher(bullets); 
       
       ArrayList<String> foundAcros = new ArrayList<String>();

       // Print starting and ending indexes of the pattern 
       // in text 
       while (m.find()) {
           System.out.println("Pattern found from " + m.start() + " to " + (m.end()-1)); 
           System.out.println(m.group(0));
           foundAcros.add(m.group(0));
       }
       System.out.println("Array List: ");
       for(String x : foundAcros){
       System.out.println(x);
    }
        Collections.sort(foundAcros);
        
        //
        //String[] acs = {"AAs","CGO","NCO"};
        for (String ac : foundAcros) {
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

