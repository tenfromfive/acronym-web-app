package org.alohaspark.tron.acronymwebapp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(MockitoJUnitRunner.class)
public class AcronymServiceTest {
	
	@Mock
	private AcronymRepository repository;
	
	@InjectMocks
	private AcronymService service;
	
	@Test
	public void shouldReturnAcronymList() {
		
		Mockito.when(repository.findByName("AA")).thenReturn(Acronym.createSingleDefAcronym("AA","AA Definition", true));
		
		List<Acronym> acronyms = service.findAcronyms("AA");
		
		assertEquals("AA Definition", acronyms.get(0).getDefinitions().get(0).getDefinition());
		
	}

	@Test
	public void shouldFilterDuplicates(){

		Mockito.when(repository.findByName("AA")).thenReturn(Acronym.createSingleDefAcronym("AA","AA Definition", true));

		List<Acronym> acronyms = service.findAcronyms("AAandAA");

		assertEquals(1, acronyms.size());

	}

	@Test
	public void shouldReturnNewAcronym(){
		//given: name and defintion
		//when: name is not found in database.
		//then: input the new acronym object into the DB.



		Map<String, String> testJSON = new HashMap<String, String>();

		testJSON.put("name", "BB");
		testJSON.put("definition", "Badmin Bassist");

		Acronym acronym = Acronym.createSingleDefAcronym(testJSON.get("name"), testJSON.get("definition"), false);

//		Mockito.when(repository.save(acronym)).thenReturn(null);
		Acronym testAcronym = service.addAcronym(testJSON);



		assertEquals(acronym.getName(),testAcronym.getName());
		assertEquals(acronym.getDefinitions().get(0).getDefinition(),testAcronym.getDefinitions().get(0).getDefinition());
	}








	@Test
	public void addDefinintionToAcronym(){

		Acronym acronym = Acronym.createSingleDefAcronym("AA","Admin Assist", true);

		Mockito.when(repository.findByName("AA")).thenReturn(acronym);

		Map<String, String> testJSON = new HashMap<String, String>();

		testJSON.put("name", "AA");
		testJSON.put("definition", "Different Definition");

		Acronym testAcronym = service.addAcronym(testJSON);

		assertEquals(2, testAcronym.getDefinitions().size());
	}
}
