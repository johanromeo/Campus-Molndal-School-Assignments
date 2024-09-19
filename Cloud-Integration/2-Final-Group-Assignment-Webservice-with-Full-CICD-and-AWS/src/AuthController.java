package com.jromeo.webservice.auth.controller;

import com.jromeo.webservice.auth.dto.JWTAuthResponse;
import com.jromeo.webservice.auth.dto.LoginDTO;
import com.jromeo.webservice.auth.dto.RegisterDTO;
import com.jromeo.webservice.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Johan Romeo
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO dto) {

        String token = authService.login(dto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {

        String response = authService.register(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
