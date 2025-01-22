package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.dto.DataAuthenticationUser;
import com.alurachallenge.forohub.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String autenticarUsuario(DataAuthenticationUser dataAuthenticationUser) {
        Authentication authauthentication = new UsernamePasswordAuthenticationToken(
                dataAuthenticationUser.email(), dataAuthenticationUser.password());
        var usuarioAutenticado = authenticationManager.authenticate(authauthentication);
        Usuario usuario = (Usuario) usuarioAutenticado.getPrincipal();
        return tokenService.generarToken(usuario.getEmail(), usuario.getId());
    }
}
