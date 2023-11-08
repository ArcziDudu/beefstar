package com.beefstar.beefstar.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor

public class JwtResponse {
    private UserInfo userInfo;
    private String jwtToken;

}
