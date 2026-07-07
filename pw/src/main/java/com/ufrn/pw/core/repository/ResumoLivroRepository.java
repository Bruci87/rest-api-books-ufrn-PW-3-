package com.ufrn.pw.core.repository;

import com.ufrn.pw.core.domain.ResumoLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumoLivroRepository extends JpaRepository<ResumoLivro, Long> {}