package org.alohaspark.tron.acronymwebapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.omg.CosNaming.NamingContextOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sun.java2d.pipe.AAShapePipe;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AcronymController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AcronymWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void acronymsAPIContainsOnlyAcronyms() throws Exception {

        // Success for this test case means:
        // (1) all acronyms are filtered & returned by the API
        // (2) no non-acronyms from the test case are returned
        // (3) does not care about order or syntax
        // Note: this test case is very simple.
        MvcResult result = this.mockMvc.perform(get("/acronyms?bullets=AA%20and%20CGO%20and%20NCO"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("acronyms"))
                .andReturn();

        //convert API response to String for tests
        String content = result.getResponse().getContentAsString();

        //Consider replacing with Matcher object when we learn more....
        //We do this with four separate statements rather than a single boolean
        //so that junit tells us which acronym failed
        Assert.assertTrue(content.contains("AA"));
        Assert.assertTrue(content.contains("CGO"));
        Assert.assertTrue(content.contains("NCO"));
        Assert.assertFalse(content.contains("and"));

    }

}
