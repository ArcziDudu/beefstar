package com.beefstar.beefstar.infrastructure.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class JwtRequest {
    private String userName;
    private String userPassword;


}
