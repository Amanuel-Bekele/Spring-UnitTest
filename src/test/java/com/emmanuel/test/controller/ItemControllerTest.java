package com.emmanuel.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

   private RequestBuilder builder = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);


    /*
     * the content().json() method checks just the items available in the returned json string
     * whereas content().string() checks for match of entire String.
     * <p>
     * .json() method uses JSONAssert framework behind the scenes.
     *
     * @throws Exception
     */

    @Test
    public void getDummyItemTest() throws Exception {

        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Ball\",\n" +
                "    \"price\": 10,\n" +
                "    \"quantity\": 100 \n" +
                "}";

        mockMvc.perform(builder).andExpect(MockMvcResultMatchers.content()
                .json(expected, true))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();


    }

    @Test
    public void getDummyItemTest_jsonAssert() throws Exception {

        /*
            strict false -> only check that key-value pairs match
            strict true -> All Each key value pairs must exist and match.
         */

        String expected = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
        String expected_OkayFor_strict_false_notOkFor_strict_true =
                "{\"id\":1,\"name\":\"Ball\",\"price\":10}";


        RequestBuilder builder = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(builder).andReturn();

        JSONAssert.assertEquals(expected_OkayFor_strict_false_notOkFor_strict_true,
                mvcResult.getResponse().getContentAsString(), false);


    }
}
