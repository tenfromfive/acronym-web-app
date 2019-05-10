package org.alohaspark.tron.acronymwebapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.omg.CosNaming.NamingContextOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sun.java2d.pipe.AAShapePipe;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AcronymController.class)
//@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AcronymWebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	// Since test package is not a subpackage of the class with
	// @SpringBootApplication, MongoDB repositories will
	// not create a bean. We have to do this manually here with these two mock beans
	// (comment out and see what
	// happens to learn more).

	@MockBean
	private AcronymService service;

	/*
	 *
	 * @MockBean private MongoTemplate mongoTemplate;
	 */

	@Test
	public void testHttpAcronymResponse() throws Exception {
		// setup acronym list to return from service method
		Acronym aaAcronym = Acronym.createSingleDefAcronym("AA", "AA Definition", true);
		List<Acronym> acronyms = new ArrayList<Acronym>();
		acronyms.add(aaAcronym);

		// mock out the service method
		when(this.service.findAcronyms("AA")).thenReturn(acronyms);

		// test the controller method
		this.mockMvc.perform(get("/acronyms").param("bullets", "AA")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(
"{\"acronyms\":[{\"name\":\"AA\",\"definitions\":[{\"definition\":\"AA Definition\",\"verified\":true}]}]}"));
	}


//	@Test
//	public void testHttpAcronymResponse() throws Exception {
//		// setup acronym list to return from service method
//
//	}

	/*
	 * @Test public void acronymsAPIContainsOnlyAcronyms() throws Exception {
	 * 
	 * // Success for this test case means: // (1) all acronyms are filtered &
	 * returned by the API // (2) no non-acronyms from the test case are returned //
	 * (3) does not care about order or syntax // Note: this test case is very
	 * simple. MvcResult result =
	 * this.mockMvc.perform(get("/acronyms?bullets=AA%20and%20CGO%20and%20NCO"))
	 * .andDo(print()) .andExpect(status().isOk()) .andDo(document("acronyms"))
	 * .andReturn();
	 * 
	 * //convert API response to String for tests String content =
	 * result.getResponse().getContentAsString();
	 * 
	 * //Consider replacing with Matcher object when we learn more.... //We do this
	 * with four separate statements rather than a single boolean //so that junit
	 * tells us which acronym failed Assert.assertTrue(content.contains("AA"));
	 * Assert.assertTrue(content.contains("CGO"));
	 * Assert.assertTrue(content.contains("NCO"));
	 * Assert.assertFalse(content.contains("and"));
	 * 
	 * }
	 */

	/*
	 * @Test public void returnsBlankDefinitionWhenUnkown() throws Exception {
	 * 
	 * String[] testAc = {"ASDF","QWER","HJKL"};
	 * 
	 * MvcResult result = this.mockMvc.perform(get("/acronyms?bullets=" + testAc[0]
	 * + "%20and%20" + testAc[1] + "%20and%20" + testAc[2])) .andDo(print())
	 * .andExpect(status().isOk()) .andDo(document("acronyms")) .andReturn();
	 * 
	 * String content = result.getResponse().getContentAsString();
	 * 
	 * Assert.assertTrue(content.contains("{\"name\":\"" + testAc[0] +
	 * "\",\"definition\":\"\"}"));
	 * Assert.assertTrue(content.contains("{\"name\":\"" + testAc[1] +
	 * "\",\"definition\":\"\"}"));
	 * Assert.assertTrue(content.contains("{\"name\":\"" + testAc[2] +
	 * "\",\"definition\":\"\"}")); }
	 */

}
