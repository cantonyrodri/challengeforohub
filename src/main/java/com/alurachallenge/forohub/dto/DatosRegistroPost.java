package com.alurachallenge.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroPost(
        @NotNull Long usuario_id,
        @NotBlank String titulo,
        @NotBlank String contenido,
        @NotBlank String autor,
        //@NotBlank LocalDateTime fechaDeCreacion,
        @NotBlank String categoria
) {
}
