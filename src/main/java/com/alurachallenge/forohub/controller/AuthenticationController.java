package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.dto.DataAuthenticationUser;
import com.alurachallenge.forohub.jwt.DatosJWT;
import com.alurachallenge.forohub.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<DatosJWT> autenticarUsuario(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser) {
        var jwtToken = authenticationService.autenticarUsuario(dataAuthenticationUser);
        return ResponseEntity.ok(new DatosJWT(jwtToken));
    }
}
