package com.alura.literAlura.repository;

import com.alura.literAlura.model.DadosLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<DadosLivro, Integer> {
}
