package com.alurachallenge.forohub.dto;

import java.time.LocalDateTime;

public record DatosRespuestaPost(
        Long id,
        String titulo,
        String contenido,
        String autor,
        LocalDateTime fechaDeCreacion
) {
}
