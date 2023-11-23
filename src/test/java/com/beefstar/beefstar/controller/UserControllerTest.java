package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.controller.config.RestAssuredIntegrationTestBase;
import com.beefstar.beefstar.infrastructure.JpaImpl.UserInfoImpl;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import com.beefstar.beefstar.infrastructure.jpaRepository.NewUserJpaRepository;
import com.beefstar.beefstar.service.UserService;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest extends RestAssuredIntegrationTestBase {
@Autowired
    private UserService userService;
    @Test
    @Transactional
    void registerNewUserWhenUsernameExists() {
        List<UserInfo> all = userService.findAll();
        assertEquals(4, all.size());
        String body = "{\"userName\":\"artek\",\"userFirstName\":\"test\",\"userLastName\":\"test\",\"userPassword\":\"test\"}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://localhost:" + port + basePath + "/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        List<UserInfo> allAfter = userService.findAll();
        assertEquals(4, allAfter.size());
    }

    @Test
    @Transactional
    void registerNewUser() {
        List<UserInfo> all = userService.findAll();
        assertEquals(3, all.size());
        String body = "{\"userName\":\"tester\",\"userFirstName\":\"testowy\",\"userLastName\":\"test\",\"userPassword\":\"test\"}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://localhost:" + port + basePath + "/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
        List<UserInfo> allAfter = userService.findAll();
        assertEquals(4, allAfter.size());
    }

}