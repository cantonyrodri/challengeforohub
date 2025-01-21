package com.alurachallenge.forohub.mapper;

import com.alurachallenge.forohub.dto.DatosListadoPost;
import com.alurachallenge.forohub.dto.DatosRespuestaPost;
import com.alurachallenge.forohub.model.Post;

public class PostMapper {

    // Constructor privado para evitar la instanciación de esta clase de utilidad
    private PostMapper() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no debe ser instanciada.");
    }

    public DatosRespuestaPost toDatosRespuestaPost(Post post) {
        return new DatosRespuestaPost(
                post.getId(),
                post.getTitulo(),
                post.getContenido(),
                post.getAutor(),
                post.getFechaDeCreacion()
        );
    }

    public DatosListadoPost toDatosListadoPost(Post post) {
        return new DatosListadoPost(
                post.getId(),
                post.getAutor(),
                post.getTitulo(),
                post.getContenido(),
                post.getFechaDeCreacion()
        );
    }
}
