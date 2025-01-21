package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.dto.DatosActualizarPost;
import com.alurachallenge.forohub.dto.DatosListadoPost;
import com.alurachallenge.forohub.dto.DatosRegistroPost;
import com.alurachallenge.forohub.dto.DatosRespuestaPost;
import com.alurachallenge.forohub.mapper.PostMapper;
import com.alurachallenge.forohub.model.Post;
import com.alurachallenge.forohub.model.Usuario;
import com.alurachallenge.forohub.repository.PostRepository;
import com.alurachallenge.forohub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public DatosRespuestaPost registrarPost(DatosRegistroPost datosRegistroPost) {
        Usuario usuario = usuarioRepository.findById(datosRegistroPost.usuario_id())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Post post = new Post(datosRegistroPost, usuario);
        postRepository.save(post);
        return postMapper.toDatosRespuestaPost(post);
    }

    public DatosRespuestaPost obtenerPostPorId(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));
        return postMapper.toDatosRespuestaPost(post);
    }

    public Page<DatosListadoPost> listarPost(Pageable pageable) {
        return postRepository.findByEstadoTrue(pageable).map(postMapper::toDatosListadoPost);
    }

    public DatosRespuestaPost actualizarPost(Long id, DatosActualizarPost datosActualizarPost) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        if (datosActualizarPost.titulo() != null) {
            post.setTitulo(datosActualizarPost.titulo());
        }
        if (datosActualizarPost.contenido() != null) {
            post.setContenido(datosActualizarPost.contenido());
        }

        postRepository.save(post);
        return postMapper.toDatosRespuestaPost(post);
    }

    public void eliminarPost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Post no encontrado");
        }
        postRepository.deleteById(id);
    }
}
