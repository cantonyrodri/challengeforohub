package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post>findByEstadoTrue(Pageable pageable);
}
