package com.emmanuel.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * the content().json() method checks just the items available in the returned json string
     * whereas content().string() checks for match of entire String.
     * @throws Exception
     */

    @Test
    public void getDummyItemTest() throws Exception {

        RequestBuilder builder = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder).andExpect(MockMvcResultMatchers.content().json("{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Ball\",\n" +
                "    \"price\": 10,\n" +
                "    \"quantity\": 100\n" +
                "}")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }
}
