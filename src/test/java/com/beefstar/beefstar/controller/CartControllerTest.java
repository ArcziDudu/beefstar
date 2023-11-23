package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.controller.config.RestAssuredIntegrationTestBase;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtRequest;
import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class CartControllerTest extends RestAssuredIntegrationTestBase {
    @Test
    public void thatThrowUnauthorizedWhenAdminFetchDeatails(){
        JwtRequest jwtRequest = new JwtRequest("AdminBeefStar", "admin@pass");
        String tokenAdmin = given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .jsonPath()
                .get("jwtToken");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .get("http://localhost:" + port + basePath + "/cartDetails");

        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void thatFetchCorrectWhenUserFetchDetails(){

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:" + port + basePath + "/cartDetails");

        response.then().statusCode(HttpStatus.SC_OK)
                .body("product.size()", equalTo(0));

        Response responseAddToCart = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("http://localhost:" + port + basePath + "/addToCart/4");


        Cart cart = responseAddToCart.as(Cart.class);
        assertEquals(1, cart.getCartId());
        assertEquals("smoked rib", cart.getProduct().getProductName());
        assertEquals("cold-meat", cart.getProduct().getProductCategory());
        assertEquals("13.00", cart.getProduct().getProductActualPrice());

        Response responseAfter = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:" + port + basePath + "/cartDetails");

        responseAfter.then().statusCode(HttpStatus.SC_OK)
                .body("product.size()", equalTo(1));

    }
}