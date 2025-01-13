package com.alura.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.Map;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public record FormatosArquivo(
        @JsonAlias("*")
        @ElementCollection
        Map<String, String> formatos
) {}
