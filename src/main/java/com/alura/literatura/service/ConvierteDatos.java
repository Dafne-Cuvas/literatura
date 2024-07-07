package com.alura.literatura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConvierteDatos {

    private final ObjectMapper objectMapper= new ObjectMapper();

    public <T> T obtenerDatos(String json, Class<T> clazz){
        try {
            return objectMapper.readValue(json,clazz);

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
