package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.entity.Role;
import com.beefstar.beefstar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    public static final String NEW_ROLE = "/createNewRole";
    @Autowired
    private RoleService roleService;
    @PostMapping(value = NEW_ROLE)
    public Role createNewRole(@RequestBody Role role){
       return roleService.createNewRole(role);
    }
}
