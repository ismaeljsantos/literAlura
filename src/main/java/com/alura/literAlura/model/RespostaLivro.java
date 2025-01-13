package com.alura.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record RespostaLivro(
        @JsonAlias("count") int totalLivros,
        @JsonAlias("next") String proximaPagina,
        @JsonAlias("previous") String paginaAnterior,
        @JsonAlias("results") List<DadosLivro> livros
        ) {
}
