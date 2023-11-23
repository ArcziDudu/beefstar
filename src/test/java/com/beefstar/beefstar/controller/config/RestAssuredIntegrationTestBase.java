package com.beefstar.beefstar.controller.config;

import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Import(PersistenceContainerTestConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class RestAssuredIntegrationTestBase
        extends AbstractIT{
    @Autowired
    protected ObjectMapper objectMapper;

    private String jSessionIdValue;


    @Test
    void contextLoaded() {
        assertThat(true).isTrue();
    }


    public RequestSpecification requestSpecification() {
        return restAssuredBase()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", jSessionIdValue);
    }

    public RequestSpecification requestSpecificationNoAuthentication() {
        return restAssuredBase();
    }

    private RequestSpecification restAssuredBase() {
        return RestAssured
                .given()
                .config(getConfig())
                .basePath(basePath)
                .port(port);
    }

    private RestAssuredConfig getConfig() {
        return RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig()
                        .jackson2ObjectMapperFactory((type, s) -> objectMapper));
    }
    private String body = "{\"userName\":\"artek\",\"userFirstName\":\"Artur\",\"userLastName\":\"Augustyn\",\"userPassword\":\"test\"}";
    JwtRequest jwtRequest = new JwtRequest("artek", "test");
    public String token ="";
    @BeforeAll
    @Transactional
    public void setup(){

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://localhost:" + port + basePath + "/register")
                .then()
                .assertThat();

        token  = given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .jsonPath()
                .get("jwtToken");
    }

}
