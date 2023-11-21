package com.beefstar.beefstar.domain;

import com.beefstar.beefstar.infrastructure.entity.Cart;
import lombok.Builder;

import java.util.Set;

@Builder

public record UserInfoDTO(String userName, String userFirstName, String userLastName, String userPassword,
                          Set<RoleDTO> role) {
}
