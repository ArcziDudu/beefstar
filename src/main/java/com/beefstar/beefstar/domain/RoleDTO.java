package com.beefstar.beefstar.domain;

import lombok.Builder;

@Builder
public record RoleDTO(String roleName, String roleDescription) {}
