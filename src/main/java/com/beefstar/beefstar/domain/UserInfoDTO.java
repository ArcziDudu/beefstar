package com.beefstar.beefstar.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.Set;

@Builder

public record UserInfoDTO(String userName, String userFirstName, String userLastName, String userPassword,
                          Set<RoleDTO> role) {
}
