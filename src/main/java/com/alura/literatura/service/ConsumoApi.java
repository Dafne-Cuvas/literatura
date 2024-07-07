package com.alura.literatura.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumoApi {
    private final RestTemplate restTemplate = new RestTemplate();

    public String obtenerDatosDeApi(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
