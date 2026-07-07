package com.ufrn.pw.core.repository;

import com.ufrn.pw.core.domain.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>, RevisionRepository<Livro, Long, Integer> {
    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}