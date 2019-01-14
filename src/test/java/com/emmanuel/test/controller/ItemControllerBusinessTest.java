package com.emmanuel.test.controller;

import com.emmanuel.test.model.Item;
import com.emmanuel.test.service.ItemService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.isEmptyString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemControllerWithService.class) // <-- Will only construct ItemControllerWithService class bean
//     Any dependent beans must be mocked.

public class ItemControllerBusinessTest {

    @MockBean
    private ItemService service;

    @Autowired
    private MockMvc mockMvc;


    private String expectedJSON = "{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Ball\",\n" +
            "    \"price\": 10,\n" +
            "    \"quantity\": 100\n" +
            "}";

    private Item item = new Item(1, "Ball", 10, 100);

    @Test
    public void testItemController_basic() throws Exception {

        Mockito.when(service.getDummyItem()).thenReturn(item);

        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders
                .get("/item")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(accept).andExpect(MockMvcResultMatchers.content().json(expectedJSON));

    }


    @Test
    public void testItemController() throws Exception {

        String expectedJSON = "[{\"id\":1012,\"name\":\"John Doe\",\"price\":120,\"quantity\":78}]";

        List<Item> dummy = Collections.singletonList(new Item(1012, "John Doe", 120, 78));
        Mockito.when(service.getItemsList()).thenReturn(dummy);

        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.get("/item").accept(MediaType.APPLICATION_JSON);

        // can be changed to .perform(accept).andExpect(content().json(expectedJSON)).andExpect(status().isOk())
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();

        // This will fail if strict was set to true (cuz value is returned which we don't have in the expected res.)
        JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false);

        Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }


    @Test
    public void testItemController_postItem() throws Exception {
        Item dummyItem = new Item(1012, "John Doe", 120, 78);

        ResponseEntity<?> dummyResponse = new ResponseEntity<>(dummyItem, HttpStatus.CREATED);

        // TROUBLE MAKER
//        Mockito.when(service.addItem(Mockito.any(Item.class))).thenReturn(dummyResponse);

        RequestBuilder builder = MockMvcRequestBuilders.post("/item")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":1012,\"name\":\"John Doe\",\"price\":120,\"quantity\":78}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"id\":1012,\"name\":\"John Doe\",\"price\":120,\"quantity\":78, \"value\":9360}", false))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
    }

    /*
        3 libraries to write assertions
            = AssertJ
            = Hamcrest Assertion
            = JSONPath
     */


    @Test
    public void hamcrestDemo(){
        List<String> stringList = Arrays.asList("some", "dummy", "list", "of", "strings");
        List<Integer> integerList = Arrays.asList(54,87, 65, 12);

        Assert.assertThat(integerList, hasItem(54));
        Assert.assertThat(integerList, hasItems(87, 65));
        Assert.assertThat(integerList, everyItem(greaterThan(12)));
        Assert.assertThat(integerList, everyItem(Matchers.lessThan(63)));

        Assert.assertThat(stringList.get(0), isEmptyString());
        Assert.assertThat(stringList.get(0), containsString("some"));
        Assert.assertThat(stringList.get(0), endsWith("end"));


    }

}
