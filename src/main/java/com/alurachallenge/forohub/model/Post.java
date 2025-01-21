package com.alurachallenge.forohub.model;

import com.alurachallenge.forohub.dto.DatosActualizarPost;
import com.alurachallenge.forohub.dto.DatosRegistroPost;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
//@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String contenido;
    private String autor;
    private LocalDateTime fechaDeCreacion;
    //private LocalDateTime fechaDeModificacion;
    private String categoria;
    private Boolean estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructor con todos los atributos
    public Post(Long id, String titulo, String contenido, String autor,
                LocalDateTime fechaDeCreacion, String categoria, Boolean estado, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = autor;
        this.fechaDeCreacion = fechaDeCreacion;
        this.categoria = categoria;
        this.estado = estado;
        this.usuario = usuario;
    }

    // Constructor
    public Post(@Valid DatosRegistroPost datosRegistroPost, Usuario usuario) {
        this.usuario = usuario;
        this.titulo = datosRegistroPost.titulo();
        this.contenido = datosRegistroPost.contenido();
        this.autor = datosRegistroPost.autor();
        this.fechaDeCreacion = LocalDateTime.now();
        this.categoria = datosRegistroPost.categoria();
        this.estado = true;
    }

    // Métodos de actualización
    public void actualizarDatosPost(@Valid DatosActualizarPost datosActualizarPost) {
        if (datosActualizarPost.autor() != null) {
            this.autor = datosActualizarPost.autor();
        }
        if (datosActualizarPost.titulo() != null) {
            this.titulo = datosActualizarPost.titulo();
        }
        if (datosActualizarPost.contenido() != null) {
            this.contenido = datosActualizarPost.contenido();
        }
    }

    // Metodo para desactivar el Post
    public void desactivarPost() {
        this.estado = false;
    }


    /*
    // Métodos de actualización de Post Especifico
    public void actualizarDatosEspecificoPost(@Valid DatosActualizarEspecificoPost datosActualizarEspecificoPost) {
        if (datosActualizarEspecificoPost.autor() != null) {
            this.autor = datosActualizarEspecificoPost.autor();
        }
        if (datosActualizarEspecificoPost.titulo() != null) {
            this.titulo = datosActualizarEspecificoPost.titulo();
        }
        if (datosActualizarEspecificoPost.contenido() != null) {
            this.contenido = datosActualizarEspecificoPost.contenido();
        }
    }
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
