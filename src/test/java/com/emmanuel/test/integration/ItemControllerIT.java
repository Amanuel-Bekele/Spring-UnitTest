package com.emmanuel.test.integration;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // <-- Launches up the main container
// Spring will pick a port which is not in use.
@TestPropertySource(locations = {"classpath:test-config.properties"}) // <-- Overrides properties provided in the application
// or the OS
public class ItemControllerIT {

    @Autowired // <-- will be aware of the port being used.
    private TestRestTemplate restTemplate;


    @Test
    public void contextLoad() throws JSONException {
        String expectedJSON = "[{}, {}, {}, {}, {}]";
        String response = restTemplate.getForObject("/item", String.class);
        JSONAssert.assertEquals(expectedJSON, response, false);
    }


}
