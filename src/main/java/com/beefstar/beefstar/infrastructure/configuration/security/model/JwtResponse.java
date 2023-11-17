package com.beefstar.beefstar.infrastructure.configuration.security.model;

import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private final UserInfo userInfo;
    private final String jwtToken;

}
