package com.se300.ledger.controller;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Account;
import com.se300.ledger.model.Store;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(classes = TestSmartStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreRestControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static HttpHeaders headers;

    @BeforeAll
    static void init(){

        headers = new HttpHeaders();
        headers.setBasicAuth("sergey", "chapman");
    }

    @Test
    public void testGetStoreById() throws IllegalStateException, JSONException {

        String expectedJson = "{\"id\":1,\"address\":\"75 Forbes\",\"description\":\"My First Store\"}";

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/stores/1", HttpMethod.GET, new HttpEntity<String>(headers),
                String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }

    @Test
    public void testPostStore() throws IllegalStateException, JSONException {

        String expectedJson = "{\"id\":2,\"address\":\"1919 Ruhland Ave\",\"description\":\"My Second Store\"}";

        Store store = new Store(Long.getLong("2"), "1919 Ruhland Ave", "My Second Store");

        HttpEntity<Store> request = new HttpEntity<>(store, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/stores", request, String.class);
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }
}
