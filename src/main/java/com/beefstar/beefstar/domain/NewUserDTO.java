package com.beefstar.beefstar.domain;

import lombok.Builder;

import java.util.Set;

@Builder
public record NewUserDTO(String userName, String userFirstName, String userLastName, String userPassword,
                         Set<RoleDTO> role) {
}
