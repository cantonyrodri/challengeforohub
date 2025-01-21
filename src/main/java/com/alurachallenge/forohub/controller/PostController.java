package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.dto.DatosActualizarPost;
import com.alurachallenge.forohub.dto.DatosListadoPost;
import com.alurachallenge.forohub.dto.DatosRegistroPost;
import com.alurachallenge.forohub.dto.DatosRespuestaPost;
import com.alurachallenge.forohub.repository.PostRepository;
import com.alurachallenge.forohub.repository.UsuarioRepository;
import com.alurachallenge.forohub.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPost> registrarPost(
            @RequestBody @Valid DatosRegistroPost datosRegistroPost,
            UriComponentsBuilder uriComponentsBuilder) {

        DatosRespuestaPost respuesta = postService.registrarPost(datosRegistroPost);
        URI url = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPost>> listarPost(@PageableDefault(size = 2)  Pageable pageable) {
        return ResponseEntity.ok(postService.listarPost(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPost> obtenerPostPorId(@PathVariable Long id) {
        return ResponseEntity.ok(postService.obtenerPostPorId(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaPost> actualizarPost(@PathVariable Long id,
            @RequestBody @Valid DatosActualizarPost datosActualizarPost) {

        return ResponseEntity.ok(postService.actualizarPost(id, datosActualizarPost));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarPost(@PathVariable Long id) {
        postService.eliminarPost(id);
        return ResponseEntity.noContent().build();
    }
}
