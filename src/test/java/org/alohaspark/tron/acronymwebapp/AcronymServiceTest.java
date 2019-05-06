package org.alohaspark.tron.acronymwebapp;

import static org.junit.Assert.*;

import java.util.List;

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
		
		Mockito.when(repository.findByName("AA")).thenReturn(new Acronym("AA","AA Definition"));
		
		List<Acronym> acronyms = service.findAcronyms("AA");
		
		assertEquals("AA Definition", acronyms.get(0).getDefinition());
		
	}

}
