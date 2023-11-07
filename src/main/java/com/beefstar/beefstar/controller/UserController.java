package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.entity.NewUser;
import com.beefstar.beefstar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    public static final String REGISTER = "/register";
    @Autowired
    private UserService userService;

    @PostMapping({"/register"})
    public NewUser registerNewUser(@RequestBody NewUser newUser){
        return userService.registerNewUser(newUser);
    }
}
