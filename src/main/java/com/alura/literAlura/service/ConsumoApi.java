package com.alura.literAlura.service;

import com.alura.literAlura.config.ApiConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsumoApi {
    private final HttpClient client;

    public ConsumoApi() {
        this.client = HttpClient.newHttpClient();
    }

    public String obterDados(String endpoint, Map<String, String> parametros) {
        String parametrosUrl = parametros.entrySet().stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        String url = ApiConfig.getBaseUrl() + endpoint + "?" + parametrosUrl;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status da resposta: " + response.statusCode());
            System.out.println("Corpo da resposta: " + response.body());

            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
