package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.infrastructure.entity.Role;
import com.beefstar.beefstar.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {
    public static final String NEW_ROLE = "/createNewRole";

    private final RoleService roleService;

    @PostMapping(value = NEW_ROLE)
    public ResponseEntity<Role> createNewRole(@RequestBody RoleDTO role) {
        return ResponseEntity.ok(roleService.createNewRole(role));
    }
}
