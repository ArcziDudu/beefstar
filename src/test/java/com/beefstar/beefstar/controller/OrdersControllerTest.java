package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.controller.config.RestAssuredIntegrationTestBase;
import com.beefstar.beefstar.domain.OrderInput;
import com.beefstar.beefstar.domain.OrderProductQuantity;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtRequest;
import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor
class OrdersControllerTest extends RestAssuredIntegrationTestBase {


    private OrderInput order() {
        return OrderInput.builder()
                .orderProductQuantityList(List.of(OrderProductQuantity.builder()
                        .productId(2)
                        .quantity(10)
                        .build()))
                .userFullAddress("Testowo")
                .userFullName("tester")
                .userContactNumber("123456789")
                .build();
    }

    @Test
    @Transactional
    void shouldReturnProductDetailCheckoutWhenUser() {
        JwtRequest jwtRequestAdmin = new JwtRequest("AdminBeefStar", "admin@pass");

        String tokenAdmin = given()
                .contentType(ContentType.JSON)
                .body(jwtRequestAdmin)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .jsonPath()
                .get("jwtToken");
        Response responseStatusBefore = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .get("http://localhost:" + port + basePath + "/order/details/all/placed");
        responseStatusBefore.then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(0));

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(order())
                .when()
                .post("http://localhost:" + port + basePath + "/order/new/true");


        response.then()
                .statusCode(HttpStatus.SC_OK);

        OrderDetail orderDetailResponse = response.as(OrderDetail.class);
        assertEquals(1, orderDetailResponse.getOrderId());
        assertEquals("Placed", orderDetailResponse.getOrderStatus());
        assertEquals(new BigDecimal("150.00"), orderDetailResponse.getOrderAmount());
        assertEquals("Smoked Tenderloin", orderDetailResponse.getProduct().getProductName());
        assertEquals("cold-meat", orderDetailResponse.getProduct().getProductCategory());

        Response responseStatusAfter = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .get("http://localhost:" + port + basePath + "/order/details/all/Placed");
        responseStatusAfter.then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(1));

        Response responseStatusDeliveredBefore = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .get("http://localhost:" + port + basePath + "/order/details/all/Delivered");
        responseStatusDeliveredBefore.then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(0));

        Response responseChangeStatus= given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .patch("http://localhost:" + port + basePath + "/order/markAsDelivered/1");
        responseChangeStatus.then()
                .statusCode(HttpStatus.SC_OK);


        Response responseStatusDeliveredAfter = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .get("http://localhost:" + port + basePath + "/order/details/all/Delivered");
        responseStatusDeliveredAfter.then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(1));

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:" + port + basePath + "/order/markAsDelivered/1")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldUnauthorizedCheckoutWhenAdmin() {
        JwtRequest jwtRequest = new JwtRequest("AdminBeefStar", "admin@pass");
        String tokenAdmin = given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .jsonPath()
                .get("jwtToken");

        OrderInput orderInput = OrderInput.builder()
                .orderProductQuantityList(List.of(OrderProductQuantity.builder()
                        .productId(2)
                        .quantity(10)
                        .build()))
                .userFullAddress("Testowo")
                .userFullName("tester")
                .userContactNumber("123456789")
                .build();



        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenAdmin)
                .body(orderInput)
                .when()
                .post("http://localhost:" + port + basePath + "/order/new/true");


        response.then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);

    }

    @Test
    void thatReturnListOfOrdersForUser() {
        JwtRequest jwtRequest = new JwtRequest("ArcziDudu", "admin@pass");

        String tokenUser = given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .jsonPath()
                .get("jwtToken");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenUser)
                .when()
                .get("http://localhost:" + port + basePath + "/order/details");

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(0));

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenUser)
                .body(order().withOrderProductQuantityList(List.of(OrderProductQuantity.builder()
                        .productId(3)
                        .quantity(10)
                        .build())))
                .when()
                .post("http://localhost:" + port + basePath + "/order/new/true")
                .then()
                .statusCode(HttpStatus.SC_OK);

        Response after = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenUser)
                .when()
                .get("http://localhost:" + port + basePath + "/order/details");

        after.then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(1));

    }

    @Test
    void thatReturnUnauthorizedOrdersForAdmin() {
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
                .get("http://localhost:" + port + basePath + "/order/details");

        response.then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }


}