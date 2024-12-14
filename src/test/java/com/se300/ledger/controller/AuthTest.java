package com.se300.ledger.controller;

import com.se300.ledger.TestSmartStoreApplication;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = {
        TestSmartStoreApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthTest {

    @LocalServerPort
    private Integer port;

    @Test
    void testStoreAuth() {

        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:" + port + "/stores/1")
                .then()
                .statusCode(200)
                .extract();
    }

    @Test
    void testAccountAuth() {

        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:" + port + "/accounts/1")
                .then()
                .statusCode(200)
                .extract();
    }
}