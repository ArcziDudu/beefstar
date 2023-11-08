package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import com.beefstar.beefstar.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


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
    public ResponseEntity<UserInfo> registerNewUser(@RequestBody UserInfoDTO newUser) {
        return ResponseEntity.ok(userService.registerNewUser(newUser));
    }

    @GetMapping(FOR_ADMIN)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> forAdmin() {
        return ResponseEntity.ok("This Url is only for Admin");
    }

    @GetMapping(FOR_USER)
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String> forUser() {
        return ResponseEntity.ok("This Url is only for User");
    }
}
