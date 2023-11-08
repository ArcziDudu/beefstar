package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.domain.NewUserDTO;
import com.beefstar.beefstar.infrastructure.entity.NewUser;
import com.beefstar.beefstar.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    public static final String REGISTER = "/register";
    public static final String FOR_ADMIN = "/for-admin";
    public static final String FOR_USER = "/for-user";
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initData() {
        userService.initRolesAndUser();
    }

    @PostMapping(REGISTER)
    public ResponseEntity<NewUser> registerNewUser(@RequestBody NewUserDTO newUser) {
        return ResponseEntity.ok(userService.registerNewUser(newUser));
    }

    @GetMapping(FOR_ADMIN)
    public ResponseEntity<String> forAdmin() {
        return ResponseEntity.ok("This Url is only for Admin");
    }

    @GetMapping(FOR_USER)
    public ResponseEntity<String> forUser() {
        return ResponseEntity.ok("This Url is only for User");
    }
}
