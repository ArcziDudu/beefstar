package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.controller.config.RestAssuredIntegrationTestBase;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class ProductControllerTest extends RestAssuredIntegrationTestBase {


    @Test
    void ThatThrowErrorWhenNotAdminAddNewProduct() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productName", "example");
        jsonObject.put("productDescription", "example");
        jsonObject.put("productActualPrice", "120");
        jsonObject.put("productCategory", "example");
        String productJson = jsonObject.toString();
        String imagePath1 = "img.png";
        File file = new ClassPathResource(imagePath1).getFile();

        given()
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("product",productJson, "application/json")
                .multiPart("imageFile",file)
                .when()
                .post("http://localhost:" + port + basePath + "/product/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);

    }

    @Test
    void ThatResponseOkWhenAdminAddNewProduct() throws IOException {
        JwtRequest jwtRequest = new JwtRequest("AdminBeefStar", "admin@pass");
        String tokenAdmin = given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .jsonPath()
                .get("jwtToken");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productName", "example");
        jsonObject.put("productDescription", "example");
        jsonObject.put("productActualPrice", "120");
        jsonObject.put("productCategory", "example");
        String productJson = jsonObject.toString();
        String imagePath1 = "img.png";
        File file = new ClassPathResource(imagePath1).getFile();

        given()
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + tokenAdmin)
                .multiPart("product",productJson, "application/json")
                .multiPart("imageFile",file)
                .when()
                .post("http://localhost:" + port + basePath + "/product/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    void thatReturnAllProducts(){
        Response response = given()
                .contentType(ContentType.JSON)
                .param("pageNumber", 0)
                .param("searchKey", "")
                .when()
                .get("http://localhost:" + port + basePath + "/product/all");

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("size()", equalTo(6));
    }

    @Test
    void thatReturnAllProductsWithSearchKey(){
        Response response = given()
                .contentType(ContentType.JSON)
                .param("pageNumber", 0)
                .param("searchKey", "Smoked Tenderloin")
                .when()
                .get("http://localhost:" + port + basePath + "/product/all");

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("size()", equalTo(1));

        response.then()
                .body("[0].productName", equalTo("Smoked Tenderloin"));
    }

    @Test
    void thatReturnOneProductById(){
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:" + port + basePath + "/product/details/1");
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("productId", equalTo(1))
                .and().body("productName", equalTo("Old-Fashioned Bacon"))

                .and()
                .body("productActualPrice", equalTo("10.00"))
                .and()
                .body("productCategory", equalTo("cold-meat"));
    }

    @Test
    void thatReturnProductDetailCheckoutWhenUser(){
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:" + port + basePath + "/product/order/true/1");

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("[0].productId", equalTo(1))
                .and()
                .body("[0].productName", equalTo("Old-Fashioned Bacon"))
                .and()
                .body("[0].productActualPrice", equalTo("10.00"))
                .and()
                .body("[0].productCategory", equalTo("cold-meat"));
    }
    @Test
    void thatReturnUnauthorizedDetailCheckoutWhenAdmin(){
        JwtRequest jwtRequest = new JwtRequest("AdminBeefStar", "admin@pass");
        String tokenAdmin = given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .jsonPath()
                .get("jwtToken");

        Response response = given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .get("http://localhost:" + port + basePath + "/product/order/true/1");

        response.then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}