package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import com.beefstar.beefstar.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {
    public static final String REGISTER = "/register";

    private final UserService userService;

    @PostConstruct
    public void initData() {
        userService.initRolesAndUser();
    }

    @PostMapping(REGISTER)
    public ResponseEntity<UserInfo> registerNewUser(@RequestBody UserInfoDTO newUser) {
        return ResponseEntity.ok(userService.registerNewUser(newUser));
    }

}
