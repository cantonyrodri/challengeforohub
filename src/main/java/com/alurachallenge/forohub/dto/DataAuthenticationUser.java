package com.alurachallenge.forohub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataAuthenticationUser(
        @Email String email,
        @NotBlank String password
) {
}
