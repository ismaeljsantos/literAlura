package com.alura.literAlura.service;

import com.alura.literAlura.model.DadosLivro;
import com.alura.literAlura.model.RespostaLivro;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LivroService {

    private final ConsumoApi consumoApi;
    private final ObjectMapper objectMapper;

    public LivroService() {
        this.consumoApi = new ConsumoApi();
        this.objectMapper = new ObjectMapper();
    }

    public List<DadosLivro> getLivrosPorTitulo(String titulo) {
        String endpoint = "";
        Map<String, String> parametros = Map.of("search", titulo);
        String json = consumoApi.obterDados(endpoint, parametros);

        if (json == null || json.isEmpty()) {
            System.out.println("A resposta da API está vazia.");
            return Collections.emptyList();
        }

        try {
            RespostaLivro resposta = objectMapper.readValue(json, RespostaLivro.class);
            return resposta.livros();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<DadosLivro> getLivros() {
        String endpoint = "";
        Map<String, String> parametros = Collections.emptyMap();
        String json = consumoApi.obterDados(endpoint, parametros);

        if (json == null || json.isEmpty()) {
            System.out.println("A resposta da API está vazia.");
            return Collections.emptyList();
        }

        try {
            RespostaLivro resposta = objectMapper.readValue(json, RespostaLivro.class);
            return resposta.livros();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Optional<DadosLivro> getLivrosById(int id){
        String endpoint = "/" + id;
        Map<String, String> parametros = Collections.emptyMap();
        String json = consumoApi.obterDados(endpoint, parametros);
        if (json == null || json.isEmpty()){
            System.out.println("A resposta da API esá vazia");
        }

        try {
            DadosLivro livro = objectMapper.readValue(json, DadosLivro.class);
            return Optional.of(livro);
        } catch (IOException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
