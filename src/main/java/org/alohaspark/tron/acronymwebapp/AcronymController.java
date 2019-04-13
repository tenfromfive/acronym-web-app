package org.alohaspark.tron.acronymwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

@RestController
public class AcronymController {

    @Autowired
    private AcronymRepository repository;


    @CrossOrigin
    @RequestMapping("/acronyms")
    public AcronymContainer httpAcronymResponse(@RequestParam(name = "bullets", defaultValue = "") String bullets) {

       // this.saveAcronyms();
        List<Acronym> acronyms = this.findAcronyms(bullets);
        return new AcronymContainer(acronyms);

    }

    
    //NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO!!!!!!!!!!!!!!
    // private void saveAcronyms() {
    //     //repository.deleteAll();
    //     // repository.save(new Acronym("AA", "Administrative Assistant"));
    //     // repository.save(new Acronym("CGO", "Company Grade Officer"));
    //     // repository.save(new Acronym("NCO", "Non-Commissioned Officer"));
    // }

    private List<Acronym> findAcronyms(String bullets) {

        List<Acronym> acronyms = new ArrayList<Acronym>();
        
        System.out.println("Bulets: " + bullets);

       String regex1 = "[A-Z]{2,}";
       Pattern pattern = Pattern.compile(regex1); 
        
       // Search above pattern in "geeksforgeeks.org" 
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

