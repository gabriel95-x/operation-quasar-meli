package com.mercadolibre.quasaroperation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component

public class UtilMeli {

    @Autowired
    ObjectMapper objectMapper;

    public List<String> toListString(String jsonString) throws JsonProcessingException {

        try {
            String[] array = objectMapper.readValue(jsonString, String[].class);

            // Verificar la conversión imprimiendo el arreglo
            return Arrays.asList(array);
        } catch (IOException e) {
            throw e;
        }

    }

        public String writeValueAsString(List<String> message) throws JsonProcessingException {
            return objectMapper.writeValueAsString(message);
        }
}
