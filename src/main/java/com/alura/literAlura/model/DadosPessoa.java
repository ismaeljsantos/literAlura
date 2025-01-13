package com.alura.literAlura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
@Table(name = "pessoas")
public record DadosPessoa(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id,
        @JsonAlias("birth_year")
        Integer anoNascimento,
        @JsonAlias("death_year")
        Integer anoMorte,
        @JsonAlias("name")
        String nome
) {
}
