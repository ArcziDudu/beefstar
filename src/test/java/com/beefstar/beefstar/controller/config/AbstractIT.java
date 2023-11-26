package com.beefstar.beefstar.controller.config;


import com.beefstar.beefstar.BeefstarApplication;
import com.beefstar.beefstar.dao.RoleDao;
import com.beefstar.beefstar.dao.UserInfoDao;
import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtRequest;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
@Import(PersistenceContainerTestConfiguration.class)
@SpringBootTest(
        classes = BeefstarApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional

public abstract class AbstractIT {
    @LocalServerPort
    protected int port;

    @Value("${server.servlet.context-path}")
    protected String basePath;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private String body = "{\"userName\":\"testerowy\",\"userFirstName\":\"Artur\",\"userLastName\":\"Augustyn\",\"userPassword\":\"test\"}";
    JwtRequest jwtRequest = new JwtRequest("testerowy", "test");
    public String token = "";

    @BeforeAll
    @Transactional
    void afterEach() {
        initRolesAndUsers();


    }

    private void initRolesAndUsers() {
        RoleDTO adminRole = RoleDTO.builder()
                .roleName("Admin")
                .roleDescription("Admin role")
                .build();
        RoleDTO userRole = RoleDTO.builder()
                .roleName("User")
                .roleDescription("Default role for new record")
                .build();


        roleDao.save(userRole);

        UserInfoDTO adminUser = UserInfoDTO.builder()
                .userFirstName("Admin")
                .userLastName("Admin")
                .userName("AdminBeefStar")
                .userPassword(passwordEncoder.encode("admin@pass"))
                .role(Set.of(adminRole))
                .build();

        UserInfoDTO user = UserInfoDTO.builder()
                .userFirstName("Tester")
                .userLastName("User")
                .userName("testerUser")
                .userPassword(passwordEncoder.encode("admin@pass"))
                .role(Set.of(userRole))
                .build();

        userInfoDao.save(adminUser);
        userInfoDao.save(user);

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://localhost:" + port + basePath + "/register")
                .then()
                .assertThat();

        token = given()
                .contentType(ContentType.JSON)
                .body(jwtRequest)
                .when()
                .post("http://localhost:" + port + basePath + "/auth")
                .jsonPath()
                .get("jwtToken");
    }


}
