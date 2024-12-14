package com.se300.ledger.controller;

import com.se300.ledger.TestSmartStoreApplication;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest(classes = TestSmartStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestInternalMockControllerTest {

        @LocalServerPort
        private static Integer port;

        @BeforeAll
        static void init() {

                ClientAndServer.startClientAndServer(1090);

                new MockServerClient("localhost", 1090)
                                .when(
                                                request()
                                                                .withMethod("GET")
                                                                .withPath("/stores/1"),
                                                Times.unlimited(),
                                                TimeToLive.unlimited(),
                                                0)
                                .respond(
                                                response()
                                                                .withBody("{\n  \"id\":1,\"address\":\"75 Forbes\",\"description\":\"My First Store\"\n}"));

                // Mock response for /accounts/1
                new MockServerClient("localhost", 1090)
                                .when(
                                                request()
                                                                .withMethod("GET")
                                                                .withPath("/accounts/1"),
                                                Times.unlimited(),
                                                TimeToLive.unlimited(),
                                                0)
                                .respond(
                                                response()
                                                                .withBody("{\n  \"address\":\"1\",\"balance\":\"0\"\n}"));
        }

        @Test
        void testGetStoreById() throws JSONException {

                String expectedJson = "{\"id\":1,\"address\":\"75 Forbes\",\"description\":\"My First Store\"}";

                ExtractableResponse<Response> response = RestAssured
                                .given()
                                .filter(new RequestLoggingFilter())
                                .auth().basic("sergey", "chapman")
                                .contentType(ContentType.JSON)
                                .when()
                                .get("http://localhost:" + 1090 + "/stores/1")
                                .then()
                                .statusCode(200)
                                .extract();

                JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(), true);
        }

        @Test
        void testGetAccountById() throws JSONException {
                String expectedJson = "{\"address\":\"1\",\"balance\":\"0\"}";

                ExtractableResponse<Response> response = RestAssured
                                .given()
                                .filter(new RequestLoggingFilter())
                                .auth().basic("sergey", "chapman")
                                .contentType(ContentType.JSON)
                                .when()
                                .get("http://localhost:" + 1090 + "/accounts/1")
                                .then()
                                .statusCode(200)
                                .extract();

                JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(), true);
        }
}
