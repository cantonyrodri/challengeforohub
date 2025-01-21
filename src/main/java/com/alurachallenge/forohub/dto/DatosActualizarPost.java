package com.alurachallenge.forohub.dto;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarPost(
        @NotNull Long id,
        String titulo,
        String contenido,
        String autor
) {
}
