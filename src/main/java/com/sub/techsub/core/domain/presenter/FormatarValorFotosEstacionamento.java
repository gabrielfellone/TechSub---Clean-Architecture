package com.sub.techsub.core.domain.presenter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONException;

@Slf4j
@Converter(autoApply = true)
public class FormatarValorFotosEstacionamento implements AttributeConverter<String[], String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Erro para converter Array para JSON", e);
        }
    }
    @Override
    public String[] convertToEntityAttribute(String dbData) {
        log.info("VALOR: {}", dbData);
        String validJson = null;
        if(dbData.startsWith("[")){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                log.info("Entrou aqui....");
                return objectMapper.readValue(dbData, String[].class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            validJson = formatarParaJsonValido(dbData);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return formatarParaArray(validJson);
    }
    private static String formatarParaJsonValido(String malformedJson) throws JSONException {
        malformedJson = malformedJson.trim();
        String[] parts = malformedJson.replaceAll("[{}]", "").split(",");

        JSONArray jsonArray = new JSONArray();
        for (String part : parts) {
            jsonArray.put(part);
        }
        return jsonArray.toString();
    }

    private static String[] formatarParaArray(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("JSON FORMATADO: {}", json);
        try {
            return objectMapper.readValue(json, String[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error para processar o JSON", e);
        }
    }
}

