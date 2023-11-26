package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.controller.config.RestAssuredIntegrationTestBase;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtRequest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class JwtControllerTest extends RestAssuredIntegrationTestBase {
    public String token = "";

    @Test
    public void thatShouldErrorWhenNonExistingUser() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("test", "test");
        given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void thatShouldOkWhenExistingUser() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("testerowy", "test");
        given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}