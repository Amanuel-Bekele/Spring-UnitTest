package com.emmanuel.test.integration;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // <-- Launches up the main container
                                                                            // Spring will pick a port which is not in use.
public class ItemControllerIT {

    @Autowired // <-- will be aware of the port being used.
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoad() throws JSONException {
        String expected = "[{}, {}, {}, {}, {}]";
        String response = restTemplate.getForObject("/item", String.class);
        JSONAssert.assertEquals(expected, response, false);
    }


}
