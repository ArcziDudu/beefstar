package com.beefstar.beefstar.controller;


import com.beefstar.beefstar.infrastructure.configuration.security.JwtHelper;
import com.beefstar.beefstar.infrastructure.configuration.security.JwtService;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtRequest;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtController {
    private final UserDetailsService userDetailsService;

    private final JwtHelper helper;

    private final JwtService jwtService;

    @PostMapping({"/auth"})
    public ResponseEntity<JwtResponse> createJwtToken(@RequestBody JwtRequest request) throws Exception {
        return ResponseEntity.ok(jwtService.createJwtToken(request));

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
