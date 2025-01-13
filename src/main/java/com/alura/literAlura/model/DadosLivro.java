package com.alura.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public record DadosLivro(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id,
        @JsonAlias("title")
        String titulo,
        @JsonAlias("subjects")
        @ElementCollection
        List<String> assuntos,
        @JsonAlias("authors")
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "livro_autores", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
        List<DadosPessoa> autores,
        @JsonAlias("translators")
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "livro_tradutores", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn(name = "tradutor_id"))
        List<DadosPessoa> tradutores,
        @JsonAlias("bookshelves")
        @ElementCollection
        List<String> estantes,
        @JsonAlias("languages")
        @ElementCollection
        List<String> idiomas,
        @JsonAlias("copyright")
        Boolean direitosAutorais,
        @JsonAlias("media_type")
        String tipoMedia,
        @JsonAlias("formats")
        @Embedded
        FormatosArquivo formatos,
        @JsonAlias("download_count")
        int contagemDownloads
) {}
