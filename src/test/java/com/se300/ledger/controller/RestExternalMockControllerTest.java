package com.se300.ledger.controller;

import com.se300.ledger.TestSmartStoreApplication;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestSmartStoreApplication.class)
public class RestExternalMockControllerTest {
    @Test
    void testGetStoreById() throws JSONException {

        String expectedJson = "{\"id\":\"1\",\"address\":\"Lubbock\",\"description\":\"Global\"}";

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("https://67343da5a042ab85d1195422.mockapi.io/stores/1")
                .then()
                .statusCode(200)
                .extract();

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(), true);
    }

    @Test
    void testGetAccountById() throws JSONException {

        String expectedJson = "{\"address\":\"1\",\"balance\":500}";

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("https://675b43699ce247eb19361a57.mockapi.io/accounts/1")
                .then()
                .statusCode(200)
                .extract();

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(), true);

    }
}
